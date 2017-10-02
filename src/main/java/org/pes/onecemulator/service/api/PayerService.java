package org.pes.onecemulator.service.api;

import org.pes.onecemulator.dto.AbstractObjectDto;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.repository.PayerRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    public PayerDto getPayerById(UUID id) {
        log.info("getPayerById method start...");
        Payer payer = payerRepositoryService.findById(id);
        if (payer != null) {
            return convertToDto(payer);
        }
        return new PayerDto();
    }

    public PayerDto getPayerByCode(String code) {
        log.info("getPayerByCode method start...");
        Payer payer = payerRepositoryService.findByCode(code);
        if (payer != null) {
            return convertToDto(payer);
        }
        return new PayerDto();
    }

    public List<PayerDto> listPayer() {
        log.info("Payer list method start...");
        List<Payer> payers = payerRepositoryService.findAll();
        if (payers != null) {
            return convertToDto(payers);
        }
        return new ArrayList<>();
    }

    public PayerDto createPayer(PayerDto payerDto) {
        log.info("Payer create method start...");
        if (payerDto != null) {
            PayerDto result = convertToDto(payerRepositoryService.create(convertToEntity(payerDto)));
            if (result != null) {
                return result;
            }
        }
        return new PayerDto();
    }

    public List<PayerDto> createPayer(List<PayerDto> payerDtos) {
        log.info("Payer create method start...");
        if (payerDtos != null) {
            convertToEntity(payerDtos)
                    .parallelStream()
                    .filter(Objects::nonNull)
                    .forEach(payer ->  payerRepositoryService.create(payer));
        }

        return convertToDto(payerRepositoryService.findAll());
    }
    //TODO:point to refactoring startup
    public PayerDto updatePayer(PayerDto payerDto) {
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
            log.error("Payer update error: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        log.error("Payer entity is null or id is null or code is null");

        return new PayerDto();
    }

    public PayerDto deletePayer(UUID id) {
        log.info("Payer delete method start...");
        try {
            if (id != null) {
                PayerDto payerDtoTmp = convertToDto(payerRepositoryService.findById(id));
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
                        .parallelStream()
                        .map(AbstractObjectDto::getId)
                        .collect(Collectors.toList())
                );

                return payerDtoResult;
            }
        } catch (Exception e) {
            log.error("Payer delete error: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        log.warn("Delete method argument is null");

        return new PayerDto();
    }

    private PayerDto convertToDto(Payer payer) {
        return mapperFactoryService.getMapper().map(payer, PayerDto.class);
    }

    private List<PayerDto> convertToDto(List<Payer> payers) {
        return payers.parallelStream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Payer convertToEntity(PayerDto payerDto) {
        return mapperFactoryService.getMapper().map(payerDto, Payer.class);
    }

    private List<Payer> convertToEntity(List<PayerDto> payerDtos) {
        return payerDtos.parallelStream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
