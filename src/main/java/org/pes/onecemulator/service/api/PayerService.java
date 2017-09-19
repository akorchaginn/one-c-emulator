package org.pes.onecemulator.service.api;

import org.pes.onecemulator.dto.AbstractObjectDto;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.PayerRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PayerService {

    private static final Logger log = LoggerFactory.getLogger(PayerService.class);

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private PayerRepositoryService payerRepositoryService;

    public PayerDto getPayerById(UUID id) throws NotFoundEntityException {
        log.info("Payer getById method start...");
        Payer payer = payerRepositoryService.findById(id);
        if (payer != null) {
            log.info("Payer entity with id: " + id + " found");
            return convertToDto(payer);
        }
        log.info("Payer entity with id: " + id + " not found");
        throw new NotFoundEntityException(404, "Entity with id: " + id + " not found at database");
    }

    public PayerDto getPayerByCode(String code) throws NotFoundEntityException {
        log.info("Payer getById method start...");
        Payer payer = payerRepositoryService.findByCode(code);
        if (payer != null) {
            log.info("Payer entity with code: " + code + " found");
            return convertToDto(payer);
        }
        log.info("Payer entity with code: " + code + " not found");
        throw new NotFoundEntityException(404, "Payer entity with code: " + code + " not found at database");
    }

    public List<PayerDto> listPayer() throws NotFoundEntityException {
        log.info("Payer list method start...");
        List<Payer> payers = payerRepositoryService.findAll();
        if (payers.size() > 0) {
            log.info("Payer entity list count: " + payers.size());
            return convertToDto(payers);
        }
        log.warn("Payer entity list count = 0");
        throw new NotFoundEntityException(404, "Payer entity list count = 0");
    }

    public PayerDto createPayer(PayerDto payerDto) throws CreateEntityException {
        log.info("Payer create method start...");
        try {
            if (payerDto != null && payerDto.getCode() != null) {
                log.info("Payer: " + payerDto.toString());
                PayerDto result = convertToDto(payerRepositoryService.create(convertToEntity(payerDto)));
                log.info("Payer created: " + result.toString());

                return result;
            }
        } catch (Exception e) {
            log.error("Payer create error: " + e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
            throw new CreateEntityException(500, e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
        log.error("Payer entity is null or has not expenseNumber value");
        throw new CreateEntityException(500, "Payer entity is null or has not expenseNumber value");
    }

    public PayerDto updatePayer(PayerDto payerDto) throws UpdateEntityException {
        log.info("Payer update method start...");
        try {
            if (payerDto != null && payerDto.getId() != null && payerDto.getCode() != null) {
                PayerDto tmp = convertToDto(payerRepositoryService.findById(payerDto.getId()));
                if(tmp != null) {
                    log.info("Payer: " + payerDto.toString());
                    tmp.setAddress(payerDto.getAddress());
                    tmp.setCode(payerDto.getCode());
                    tmp.setFullName(payerDto.getFullName());
                    tmp.setInn(payerDto.getInn());
                    tmp.setKpp(payerDto.getKpp());
                    tmp.setName(payerDto.getName());
                    PayerDto result = convertToDto(payerRepositoryService.update(convertToEntity(tmp)));
                    log.info("Payer updated: " + result.toString());

                    return result;
                }
            }
        } catch (Exception e) {
            throw new UpdateEntityException(500, e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
        throw new UpdateEntityException(500, "Payer entity is null");
    }

    public PayerDto deletePayer(PayerDto payerDto) throws DeleteEntityException {
        log.info("Payer delete method start...");
        try {
            if (payerDto != null) {
                PayerDto payerDtoTmp = convertToDto(payerRepositoryService.findById(payerDto.getId()));
                payerDtoTmp.setDeleted(true);
                log.info("Payer " + payerDtoTmp.toString() + " set deleted");
                Set<InvoiceDto> invoiceDtos = payerDtoTmp.getInvoices();
                log.info("Size of Invoices: "+ invoiceDtos.size());
                if (invoiceDtos.size() > 0) {
                    for (InvoiceDto i : invoiceDtos) {
                        i.setDeleted(true);
                    }
                    payerDtoTmp.setInvoices(invoiceDtos);
                }
                PayerDto payerDtoResult = convertToDto(payerRepositoryService.update(convertToEntity(payerDtoTmp)));
                log.info("Payer deleted result: "
                        + "id = " + payerDtoResult.getId()
                        + "\n" + "Invoices list id = " + payerDtoResult.getInvoices()
                        .stream()
                        .map(AbstractObjectDto::getId)
                        .collect(Collectors.toList())
                );

                return payerDtoResult;
            }
        } catch (Exception e) {
            throw new DeleteEntityException(500, e.getMessage() + "\n\t" + Arrays.toString(e.getStackTrace()));
        }
        throw new DeleteEntityException(500, "Delete method argument is null");
    }

    private PayerDto convertToDto(Payer payer) {
        return mapperFactoryService.getMapper().map(payer, PayerDto.class);
    }

    private List<PayerDto> convertToDto(List<Payer> payers) {
        return payers.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Payer convertToEntity(PayerDto payerDto) {
        return mapperFactoryService.getMapper().map(payerDto, Payer.class);
    }
}
