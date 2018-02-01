package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.repository.SourceRepository;
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

    @Autowired
    private PayerRepository payerRepository;

    @Autowired
    private SourceRepository sourceRepository;

    public InvoiceModel getById(UUID id) {
        Invoice invoice = invoiceRepository.findOne(id);
        if (invoice != null) {
            return getModel(invoice);
        }

        return new InvoiceModel("Invoice with id: " + id + " not found at database.");
    }

    public InvoiceModel getByIdAndSource(UUID id, String source) {
        Invoice invoice = invoiceRepository.findOneAndSource(id, source);
        if (invoice != null) {
            return getModel(invoice);
        }

        return new InvoiceModel("Invoice with id: " + id + " and source: " + source + " not found at database.");
    }

    public InvoiceModel getByExternalId(String externalId) {
        Invoice invoice = invoiceRepository.findByExternalId(externalId);
        if (invoice != null) {
            return getModel(invoice);
        }

        return new InvoiceModel("Invoice with external id: " + externalId + " not found at database.");
    }

    public InvoiceModel getByExternalIdAndSource(String externalId, String source) {
        Invoice invoice = invoiceRepository.findByExternalIdAndSource(externalId, source);
        if (invoice != null) {
            return getModel(invoice);
        }

        return new InvoiceModel("Invoice with external id: " + externalId
                + " and source: " + source + " not found at database.");
    }

    public List<InvoiceModel> list() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    public InvoiceModel create(InvoiceModel model) {
        if (model != null && model.getSource() != null && model.getPayerCode() != null) {
            Source source = sourceRepository.findByName(model.getSource());
            Payer payer = payerRepository.findByCode(model.getPayerCode());
            if (source != null && payer != null && payer.getSources().contains(source)) {
                Invoice invoice = new Invoice();
                invoice.setPayer(payer);
                invoice.setDate(model.getDate());
                invoice.setExternalId(model.getExternalId());
                invoice.setNumber(model.getNumber());
                invoice.setNumberOq(model.getNumberOq());
                invoice.setPaymentDate(model.getPaymentDate());
                invoice.setPaymentSum(model.getPaymentSum());
                invoice.setSource(source);
                invoice.setStatus(model.getStatus());
                invoice.setSum(model.getSum());
                invoice = invoiceRepository.save(invoice);

                return getModel(invoice);
            }

            return new InvoiceModel("Invoice is null or source is null or invoice payer is null.");
        }

        return new InvoiceModel("Invoice is null or source is null.");
    }

    public InvoiceModel update(InvoiceModel model) {
        if (model != null && model.getId() != null &&
                model.getSource() != null && model.getPayerCode() != null) {

            Invoice invoice = invoiceRepository.findOne(model.getId());
            Source source = sourceRepository.findByName(model.getSource());
            Payer payer = payerRepository.findByCode(model.getPayerCode());

            if (invoice != null && source != null && payer != null && payer.getSources().contains(source)) {
                invoice.setDate(model.getDate());
                invoice.setSum(model.getSum());
                invoice.setNumberOq(model.getNumberOq());
                invoice.setNumber(model.getNumber());
                invoice.setPaymentDate(model.getPaymentDate());
                invoice.setPaymentSum(model.getPaymentSum());
                invoice.setStatus(model.getStatus());
                invoice.setExternalId(model.getExternalId());

                if (invoice.getSource() != source) {
                    invoice.setSource(source);
                }

                invoice = invoiceRepository.save(invoice);


                return getModel(invoice);
            }

            return new InvoiceModel("Invoice with id: " + model.getId() + " not found at database.");
        }

        return new InvoiceModel("Invoice is null or id is null or source is null or payer is null.");
    }

    public void delete(UUID id) {
        invoiceRepository.delete(id);
    }

    private InvoiceModel getModel(Invoice entity) {
        InvoiceModel model = new InvoiceModel();
        model.setId(entity.getId());
        model.setSum(entity.getSum());
        model.setStatus(entity.getStatus());
        model.setSource(entity.getSource().getName());
        model.setPaymentSum(entity.getPaymentSum());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPayerCode(entity.getPayer().getCode());
        model.setNumberOq(entity.getNumberOq());
        model.setExternalId(entity.getExternalId());
        model.setDate(entity.getDate());

        return model;
    }
}
