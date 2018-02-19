package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
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

    private final InvoiceRepository invoiceRepository;

    private final PayerRepository payerRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    InvoiceServiceImpl(InvoiceRepository invoiceRepository, PayerRepository payerRepository, SourceRepository sourceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.payerRepository = payerRepository;
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public InvoiceModel getById(UUID id) throws NotFoundException {
        Invoice invoice = invoiceRepository.findOne(id);
        if (invoice != null) {
            return getModel(invoice);
        }

        throw new NotFoundException(Invoice.class, id);
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
    public InvoiceModel create(InvoiceModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }

        if (model.getPayerCode() == null) {
            throw new ValidationException("Payer code is null.");
        }

        Source source = sourceRepository.findByName(model.getSource());
        if (source == null) {
            throw new NotFoundException(Source.class, "name: " + model.getSource());
        }

        Payer payer = payerRepository.findByCode(model.getPayerCode());
        if (payer == null) {
            throw new NotFoundException(Payer.class, "code: " + model.getPayerCode());
        }

        if (!payer.getSources().contains(source)) {
            throw new ValidationException("Invoice source not equal payer source.");
        }

        Invoice invoice = new Invoice();
        invoice.setSource(source);
        invoice.setDate(toCalendar(model.getDate()));
        invoice.setNumber(model.getNumber());
        invoice.setNumberOq(model.getNumberOq());
        invoice.setPayer(payer);
        invoice.setPaymentDate(toCalendar(model.getPaymentDate()));
        invoice.setPaymentSum(new BigDecimal(model.getPaymentSum()));
        invoice.setStatus(model.getStatus());
        invoice.setSum(new BigDecimal(model.getSum()));
        invoice.setExternalId(model.getExternalId());
        invoice = invoiceRepository.save(invoice);

        return getModel(invoice);
    }

    @Transactional
    @Override
    public InvoiceModel update(InvoiceModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }

        if (model.getPayerCode() == null) {
            throw new ValidationException("Payer code is null.");
        }

        Invoice invoice = invoiceRepository.findOne(model.getId());
        if (invoice == null) {
            throw new NotFoundException(Invoice.class, model.getId());
        }

        Source source = sourceRepository.findByName(model.getSource());
        if (source == null) {
            throw new NotFoundException(Source.class, "name: " + model.getSource());
        }

        Payer payer = payerRepository.findByCode(model.getPayerCode());
        if (payer == null) {
            throw new NotFoundException(Payer.class, "code: " + model.getPayerCode());
        }

        if (!payer.getSources().contains(source)) {
            throw new ValidationException("Invoice source not equal payer source.");
        }

        invoice.setSource(source);
        invoice.setDate(toCalendar(model.getDate()));
        invoice.setNumber(model.getNumber());
        invoice.setNumberOq(model.getNumberOq());
        invoice.setPayer(payer);
        invoice.setPaymentDate(toCalendar(model.getPaymentDate()));
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

    private Calendar toCalendar(LocalDate localDate) {
        return GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    }
}
