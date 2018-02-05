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
                invoice.setSource(source);
                invoice.setDate(model.getDate());
                invoice.setNumber(model.getNumber());
                invoice.setNumberOq(model.getNumberOq());
                invoice.setPayer(payer);
                invoice.setPaymentDate(model.getPaymentDate());
                invoice.setPaymentSum(model.getPaymentSum());
                invoice.setStatus(model.getStatus());
                invoice.setSum(model.getSum());
                invoice.setExternalId(model.getExternalId());
                invoice = invoiceRepository.save(invoice);

                return getModel(invoice);
            }

            return new InvoiceModel(
                    source == null
                        ? "Source with name: " + model.getSource() + " not found at database."
                        : payer == null
                            ? "Payer is null."
                            : "Invoice source not equal payer source."
            );
        }

        return new InvoiceModel(
                model == null
                        ? "Model is null."
                        : model.getSource() == null
                        ? "Source is null."
                        : "Payer code is null."
        );
    }

    public InvoiceModel update(InvoiceModel model) {
        if (model != null && model.getId() != null &&
                model.getSource() != null && model.getPayerCode() != null) {

            Invoice invoice = invoiceRepository.findOne(model.getId());
            Source source = sourceRepository.findByName(model.getSource());
            Payer payer = payerRepository.findByCode(model.getPayerCode());

            if (invoice != null && source != null && payer != null && payer.getSources().contains(source)) {
                if (invoice.getSource() != source) {
                    invoice.setSource(source);
                }
                invoice.setDate(model.getDate());
                invoice.setNumber(model.getNumber());
                invoice.setNumberOq(model.getNumberOq());
                if (invoice.getPayer() != payer) {
                    invoice.setPayer(payer);
                }
                invoice.setPaymentDate(model.getPaymentDate());
                invoice.setPaymentSum(model.getPaymentSum());
                invoice.setStatus(model.getStatus());
                invoice.setSum(model.getSum());
                invoice.setExternalId(model.getExternalId());
                invoice = invoiceRepository.save(invoice);

                return getModel(invoice);
            }

            return new InvoiceModel(
                    invoice == null
                        ? "Invoice with id: " + model.getId() + "not found at database."
                        : source == null
                            ? "Source with name: " + model.getSource() + "not found at database."
                            : payer == null
                                ? "Payer with code: " + model.getPayerCode() + " not found at database."
                                : "Invoice source not equal payer source."
            );
        }

        return new InvoiceModel(
                model == null
                    ? "Model is null."
                    : model.getId() == null
                        ? "Id is null."
                        : model.getSource() == null
                            ? "Source is null."
                            : "Payer code is null."
        );
    }

    public void delete(UUID id) {
        invoiceRepository.delete(id);
    }

    private InvoiceModel getModel(Invoice entity) {
        InvoiceModel model = new InvoiceModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setDate(entity.getDate());
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setPayerCode(entity.getPayer().getCode());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSum(entity.getPaymentSum());
        model.setStatus(entity.getStatus());
        model.setSum(entity.getSum());
        model.setExternalId(entity.getExternalId());

        return model;
    }
}
