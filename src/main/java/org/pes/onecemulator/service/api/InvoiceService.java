package org.pes.onecemulator.service.api;

import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.api.exception.CreateEntityException;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;
import org.pes.onecemulator.service.api.exception.NotFoundEntityException;
import org.pes.onecemulator.service.api.exception.UpdateEntityException;
import org.pes.onecemulator.service.repository.InvoiceRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private InvoiceRepositoryService invoiceRepositoryService;

    @Autowired
    private PayerService payerService;

    public InvoiceDto getInvoiceById(UUID id) throws NotFoundEntityException {
        log.info("Invoice getById method start...");
        InvoiceDto invoiceDto = convertToDto(invoiceRepositoryService.findById(id));
        if (invoiceDto != null) {
            log.info("Invoice entity with id: " + id.toString() + " found");

            return invoiceDto;
        }
        log.warn("Invoice entity with id: " + id.toString() + " not found");
        throw new NotFoundEntityException(404, "Entity with id: " + id + " not found at database");
    }

    public List<InvoiceDto> listInvoice() throws NotFoundEntityException {
        log.info("Invoice list method start...");
        List<Invoice> invoices = invoiceRepositoryService.findAll();
        if (invoices.size() > 0) {
            log.info("Invoice entity list count: " + invoices.size());
            return convertToDto(invoices);
        }
        log.warn("Invoice entity list count = 0");
        throw new NotFoundEntityException(404, "Invoice entity list count = 0");
    }

    public InvoiceDto createInvoice(InvoiceDto invoiceDto) throws CreateEntityException {
        log.info("Invoice create method start...");
        try {
            if (invoiceDto != null && invoiceDto.getPayerCode() != null) {
                PayerDto payerDto = payerService.getPayerByCode(invoiceDto.getPayerCode());
                if (payerDto != null) {
                    invoiceDto.setPayer(payerDto);
                    log.info("Payer: " + payerDto.toString());
                    InvoiceDto result = convertToDto(invoiceRepositoryService.create(convertToEntity(invoiceDto)));
                    log.info("Invoice created: " + result.toString());

                    return result;
                }
            }
        } catch (Exception e) {
            log.error("Invoice create error: " + e.getMessage());
            throw new CreateEntityException(500, e.getMessage());
        }
        log.error("Invoice entity is null or has not expenseNumber value");
        throw new CreateEntityException(500, "Invoice entity is null or has not expenseNumber value");
    }

    public InvoiceDto updateInvoice(InvoiceDto invoiceDto) throws UpdateEntityException {
        log.info("Invoice update method start...");
        try {
            if (invoiceDto != null && invoiceDto.getId() != null && invoiceDto.getPayerCode() != null) {
                PayerDto payerDto = payerService.getPayerByCode(invoiceDto.getPayerCode());
                if(payerDto != null && invoiceRepositoryService.findById(invoiceDto.getId()) !=  null) {
                    invoiceDto.setPayer(payerDto);
                    log.info("Payer: " + payerDto.toString());
                    InvoiceDto tmp = convertToDto(invoiceRepositoryService.findById(invoiceDto.getId()));
                    tmp.setPayer(invoiceDto.getPayer());
                    tmp.setPayerCode(invoiceDto.getPayerCode());
                    tmp.setDate(invoiceDto.getDate());
                    tmp.setSum(invoiceDto.getSum());
                    tmp.setNumberOq(invoiceDto.getNumberOq());
                    tmp.setNumber(invoiceDto.getNumber());
                    tmp.setPaymentDate(invoiceDto.getPaymentDate());
                    tmp.setPaymentSum(invoiceDto.getPaymentSum());
                    tmp.setStatus(invoiceDto.getStatus());
                    InvoiceDto result = convertToDto(invoiceRepositoryService.update(convertToEntity(tmp)));
                    log.info("Invoice updated: " + result.toString());

                    return result;
                }
            }
        } catch (Exception e) {
            throw new UpdateEntityException(500, e.getMessage());
        }
        throw new UpdateEntityException(500, "Invoice entity is null or expenseNumber is null");
    }

    public InvoiceDto deleteInvoice(UUID id) throws DeleteEntityException {
        log.info("Invoice delete method start...");
        try {
            Invoice invoice = invoiceRepositoryService.delete(id);
            if (invoice != null) {
                InvoiceDto result = convertToDto(invoice);
                log.info("Invoice with id: " + id.toString() + " deleted");
                return result;
            }
        } catch (Exception e) {
            log.info("Error Invoice with id: " + id.toString() + " deleted " + e.getMessage());
            throw new DeleteEntityException(500, "Error delete: " + e.getMessage());
        }
        throw new DeleteEntityException(500, "Error delete: merge return null O_o");
    }

    private InvoiceDto convertToDto(Invoice invoice) {
        return mapperFactoryService.getMapper().map(invoice, InvoiceDto.class);
    }

    private List<InvoiceDto> convertToDto(List<Invoice> invoices) {
        return invoices.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Invoice convertToEntity(InvoiceDto invoiceDto) {
        return mapperFactoryService.getMapper().map(invoiceDto, Invoice.class);
    }
}
