package org.pes.onecemulator.service.impl;

import org.modelmapper.ModelMapper;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    public InvoiceDto getIById(UUID id) throws Exception {
        Invoice invoice = invoiceRepository.findOne(id);
        if (invoice != null) {
            return convertToDto(invoice);
        }

        throw new Exception("Invoice with id: " + id + " not found at database.");
    }

    public InvoiceDto getByIdAndSource(UUID id, String source) throws Exception {
        Invoice invoice = invoiceRepository.findOneAndSource(id, source);
        if (invoice != null) {
            return convertToDto(invoice);
        }

        throw new Exception("Invoice with id: " + id + " and source: " + source + " not found at database.");
    }

    public InvoiceDto getByExternalId(String externalId) throws Exception {
        Invoice invoice = invoiceRepository.findByExternalId(externalId);
        if (invoice != null) {
            convertToDto(invoice);
        }

        throw new Exception("Invoice with external id: " + externalId + " not found at database.");
    }

    public InvoiceDto getByExternalIdAndSource(String externalId, String source) throws Exception {
        Invoice invoice = invoiceRepository.findByExternalIdAndSource(externalId, source);
        if (invoice != null) {
            return convertToDto(invoice);
        }

        throw new Exception("Invoice with external id: " + externalId + " and source: " + source + " not found at database.");
    }

    public List<InvoiceDto> list() {
        return convertToDto(invoiceRepository.findAll());
    }

    public InvoiceDto create(InvoiceDto invoiceDto) throws Exception {
        if (invoiceDto != null) {
            Invoice invoice = convertToEntity(invoiceDto);
            invoice = invoiceRepository.save(invoice);
            return convertToDto(invoice);
        }

        throw new Exception("Invoice is null.");
    }

    public InvoiceDto update(InvoiceDto invoiceDto) throws Exception {
        if (invoiceDto != null && invoiceDto.getId() != null) {
            Invoice invoice = invoiceRepository.findOne(invoiceDto.getId());
            if (invoice != null) {
                invoice.setDate(invoiceDto.getDate());
                invoice.setSum(invoiceDto.getSum());
                invoice.setNumberOq(invoiceDto.getNumberOq());
                invoice.setNumber(invoiceDto.getNumber());
                invoice.setPaymentDate(invoiceDto.getPaymentDate());
                invoice.setPaymentSum(invoiceDto.getPaymentSum());
                invoice.setStatus(invoiceDto.getStatus());
                invoice.setExternalId(invoiceDto.getExternalId());
                invoice = invoiceRepository.save(invoice);
                return convertToDto(invoice);
            }

            throw new Exception("Invoice with id: " + invoiceDto.getId() + " not found at database.");
        }

        throw new Exception("Invoice is null or id is null.");
    }

    public void delete(UUID id) {
        invoiceRepository.delete(id);
    }

    private InvoiceDto convertToDto(Invoice invoice) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(invoice, InvoiceDto.class);
    }

    private List<InvoiceDto> convertToDto(List<Invoice> invoices) {
        return invoices.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Invoice convertToEntity(InvoiceDto invoiceDto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(invoiceDto, Invoice.class);
    }
}
