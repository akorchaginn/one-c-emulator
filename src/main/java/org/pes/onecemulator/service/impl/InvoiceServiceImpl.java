package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.converter.InvoiceModelConverter;
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

    private static final InvoiceModelConverter INVOICE_MODEL_CONVERTER = new InvoiceModelConverter();

    private final InvoiceRepository invoiceRepository;

    private final PayerRepository payerRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    public InvoiceServiceImpl(final InvoiceRepository invoiceRepository,
                              final PayerRepository payerRepository,
                              final SourceRepository sourceRepository) {
        this.invoiceRepository = invoiceRepository;
        this.payerRepository = payerRepository;
        this.sourceRepository = sourceRepository;
    }

    @Override
    public InvoiceModel getById(final UUID id) throws NotFoundException {
        return invoiceRepository.findById(id)
                .map(INVOICE_MODEL_CONVERTER::convert)
                .orElseThrow(() -> new NotFoundException(Invoice.class, id));
    }

    @Override
    public List<InvoiceModel> list() {
        return invoiceRepository.findAll()
                .stream()
                .map(INVOICE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public InvoiceModel create(final InvoiceModel model) throws Exception {
        validateModel(model);

        return INVOICE_MODEL_CONVERTER.convert(updateOrCreate(model, new Invoice()));
    }

    @Transactional
    @Override
    public InvoiceModel update(final InvoiceModel model) throws Exception {
        validateModel(model);

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        final Invoice invoice = invoiceRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Invoice.class, model.getId()));

        return INVOICE_MODEL_CONVERTER.convert(updateOrCreate(model, invoice));
    }

    @Transactional
    @Override
    public void delete(final UUID id) {
        invoiceRepository.deleteById(id);
    }

    private Invoice updateOrCreate(final InvoiceModel model, final Invoice invoice) throws ValidationException, NotFoundException {
        final Source source = sourceRepository.findByName(model.getSource())
                .orElseThrow(() -> new NotFoundException(Source.class, "name: " + model.getSource()));

        final Payer payer = payerRepository.findByCode(model.getPayerCode())
                .orElseThrow(() -> new NotFoundException(Payer.class, "code: " + model.getPayerCode()));

        if (payer.getPayerSources().stream().noneMatch(ps -> ps.getSource().equals(source))) {
            throw new ValidationException("Invoice source not equal payer source.");
        }

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
        invoice.setPaymentCurrency(model.getPaymentCurrency());
        invoice.setCurrency(model.getCurrency());
        invoice.setPaymentSumRUB(model.getPaymentSumRUB());
        invoice.setSumRUB(model.getSumRUB());

        return invoiceRepository.save(invoice);
    }

    private void validateModel(final InvoiceModel model) throws ValidationException {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getSource() == null) {
            throw new ValidationException("Source is null.");
        }

        if (model.getPayerCode() == null) {
            throw new ValidationException("Payer code is null.");
        }
    }
}
