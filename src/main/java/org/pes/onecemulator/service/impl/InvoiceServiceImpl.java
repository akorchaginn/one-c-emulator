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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final PayerRepository payerRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, PayerRepository payerRepository, SourceRepository sourceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.payerRepository = payerRepository;
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public InvoiceModel getById(final UUID id) throws NotFoundException {
        final Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Invoice.class, id));
        return getModel(invoice);
    }

    @Transactional
    @Override
    public List<InvoiceModel> list() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public InvoiceModel create(final InvoiceModel model) throws Exception {

        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }

        if (model.getPayerCode() == null) {
            throw new ValidationException("Payer code is null.");
        }

        final Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        final Payer payer = payerRepository.findByCode(model.getPayerCode())
                .orElseThrow(() -> new NotFoundException(Payer.class, "code: " + model.getPayerCode()));

        if (!payer.getSources().contains(source)) {
            throw new ValidationException("Invoice source not equal payer source.");
        }

        Invoice invoice = new Invoice();
        invoice.setSource(source);
        invoice.setDate(model.getDate());
        invoice.setNumber(model.getNumber());
        invoice.setNumberOq(model.getNumberOq());
        invoice.setPayer(payer);
        invoice.setPaymentDate(model.getPaymentDate());
        invoice.setPaymentSum(model.getPaymentSumRUB());
        invoice.setStatus(model.getStatus());
        invoice.setSum(model.getSum());
        invoice.setExternalId(model.getExternalId());
        invoice.setPaymentCurrency(model.getPaymentCurrency());
        invoice.setCurrency(model.getCurrency());
        invoice.setPaymentSumRUB(model.getPaymentSum());
        invoice.setSumRUB(model.getSumRUB());
        invoice = invoiceRepository.save(invoice);

        return getModel(invoice);
    }

    @Transactional
    @Override
    public InvoiceModel update(final InvoiceModel model) throws Exception {

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

        final Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        final Payer payer = payerRepository.findByCode(model.getPayerCode())
                .orElseThrow(() -> new NotFoundException(Payer.class, "code: " + model.getPayerCode()));

        if (!payer.getSources().contains(source)) {
            throw new ValidationException("Invoice source not equal payer source.");
        }

        Invoice invoice = invoiceRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Invoice.class, model.getId()));

        invoice.setSource(source);
        invoice.setDate(model.getDate());
        invoice.setNumber(model.getNumber());
        invoice.setNumberOq(model.getNumberOq());
        invoice.setPayer(payer);
        invoice.setPaymentDate(model.getPaymentDate());
        invoice.setPaymentSum(model.getPaymentSumRUB());
        invoice.setStatus(model.getStatus());
        invoice.setSum(model.getSum());
        invoice.setExternalId(model.getExternalId());
        invoice.setPaymentCurrency(model.getPaymentCurrency());
        invoice.setCurrency(model.getCurrency());
        invoice.setPaymentSumRUB(model.getPaymentSum());
        invoice.setSumRUB(model.getSumRUB());
        invoice = invoiceRepository.save(invoice);

        return getModel(invoice);
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        invoiceRepository.deleteById(id);
    }

    private InvoiceModel getModel(final Invoice entity) {
        final InvoiceModel model = new InvoiceModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setDate(entity.getDate());
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setPayerCode(entity.getPayer().getCode());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSumRUB(entity.getPaymentSum());
        model.setStatus(entity.getStatus());
        model.setSum(entity.getSum());
        model.setExternalId(entity.getExternalId());
        model.setPaymentCurrency(entity.getPaymentCurrency());
        model.setCurrency(entity.getCurrency());
        model.setPaymentSum(entity.getPaymentSumRUB());
        model.setSumRUB(entity.getSumRUB());

        return model;
    }
}
