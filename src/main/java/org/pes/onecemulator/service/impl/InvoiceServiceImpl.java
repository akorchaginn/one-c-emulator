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

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Transactional
    @Override
    public InvoiceModel getById(UUID id) {
        Invoice invoice = invoiceRepository.findOne(id);
        if (invoice != null) {
            return getModel(invoice);
        }

        return new InvoiceModel("Invoice with id: " + id + " not found at database.");
    }

    @Transactional
    @Override
    public List<InvoiceModel> list() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public InvoiceModel create(InvoiceModel model) {

        if (model == null) {
            return new InvoiceModel("Model is null.");
        }

        if (model.getSource() == null) {
            return new InvoiceModel("Source is null.");
        }

        if (model.getPayerCode() == null) {
            return new InvoiceModel("Payer code is null.");
        }

        Source source = sourceRepository.findByName(model.getSource());

        if (source == null) {
            return new InvoiceModel("Source with name: " + model.getSource() + " not found at database.");
        }

        Payer payer = payerRepository.findByCode(model.getPayerCode());

        if (payer == null) {
            return new InvoiceModel("Payer with code: " + model.getPayerCode() + " not found at database.");
        }

        if (!payer.getSources().contains(source)) {
            return new InvoiceModel("Invoice source not equal payer source.");
        }

        Invoice invoice = new Invoice();
        invoice.setSource(source);
        invoice.setDate(GregorianCalendar.from(model.getDate().atStartOfDay(ZoneId.systemDefault())));
        invoice.setNumber(model.getNumber());
        invoice.setNumberOq(model.getNumberOq());
        invoice.setPayer(payer);
        invoice.setPaymentDate(GregorianCalendar.from(model.getPaymentDate().atStartOfDay(ZoneId.systemDefault())));
        invoice.setPaymentSum(new BigDecimal(model.getPaymentSum()));
        invoice.setStatus(model.getStatus());
        invoice.setSum(new BigDecimal(model.getSum()));
        invoice.setExternalId(model.getExternalId());
        invoice = invoiceRepository.save(invoice);

        return getModel(invoice);
    }

    @Transactional
    @Override
    public InvoiceModel update(InvoiceModel model) {

        if (model == null) {
            return new InvoiceModel("Model is null.");
        }

        if (model.getId() == null) {
            return new InvoiceModel("Id is null.");
        }

        if (model.getSource() == null) {
            return new InvoiceModel("Source is null.");
        }

        if (model.getPayerCode() == null) {
            return new InvoiceModel("Payer code is null.");
        }

        Invoice invoice = invoiceRepository.findOne(model.getId());

        if (invoice == null) {
            return new InvoiceModel("Invoice with id: " + model.getId() + "not found at database.");
        }

        Source source = sourceRepository.findByName(model.getSource());

        if (source == null) {
            return new InvoiceModel("Source with name: " + model.getSource() + "not found at database.");
        }

        Payer payer = payerRepository.findByCode(model.getPayerCode());

        if (payer == null) {
            return new InvoiceModel("Payer with code: " + model.getPayerCode() + "not found at database.");
        }

        if (!payer.getSources().contains(source)) {
            return new InvoiceModel("Invoice source not equal payer source.");
        }

        if (invoice.getSource() != source) {
            invoice.setSource(source);
        }
        invoice.setDate(GregorianCalendar.from(model.getDate().atStartOfDay(ZoneId.systemDefault())));
        invoice.setNumber(model.getNumber());
        invoice.setNumberOq(model.getNumberOq());
        if (invoice.getPayer() != payer) {
            invoice.setPayer(payer);
        }
        invoice.setPaymentDate(GregorianCalendar.from(model.getPaymentDate().atStartOfDay(ZoneId.systemDefault())));
        invoice.setPaymentSum(new BigDecimal(model.getPaymentSum()));
        invoice.setStatus(model.getStatus());
        invoice.setSum(new BigDecimal(model.getSum()));
        invoice.setExternalId(model.getExternalId());
        invoice = invoiceRepository.save(invoice);

        return getModel(invoice);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        invoiceRepository.delete(id);
    }

    private InvoiceModel getModel(Invoice entity) {
        InvoiceModel model = new InvoiceModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setDate(toLocalDate(entity.getDate()));
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setPayerCode(entity.getPayer().getCode());
        model.setPaymentDate(toLocalDate(entity.getPaymentDate()));
        model.setPaymentSum(entity.getPaymentSum().toString());
        model.setStatus(entity.getStatus());
        model.setSum(entity.getSum().toString());
        model.setExternalId(entity.getExternalId());

        return model;
    }

    private LocalDate toLocalDate(Calendar calendar) {
        return calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
