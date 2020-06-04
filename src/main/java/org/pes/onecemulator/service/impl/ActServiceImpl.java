package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Act;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.ActModel;
import org.pes.onecemulator.repository.ActRepository;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActServiceImpl implements ActService {

    private final ActRepository actRepository;

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public ActServiceImpl(final ActRepository actRepository, final InvoiceRepository invoiceRepository) {
        this.actRepository = actRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Act getById(final UUID id) throws NotFoundException {
        return actRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Act.class, id));
    }

    @Override
    public List<Act> list() {
        return actRepository.findAll();
    }

    @Override
    public Act create(final ActModel model) throws ValidationException, NotFoundException {
        validateModel(model);

        return updateOrCreate(model, new Act());
    }

    @Override
    public Act update(final ActModel model) throws NotFoundException, ValidationException {
        validateModel(model);

        if (model.getId() == null) {
            throw new ValidationException("Id is null");
        }

        final Act act = actRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Act.class, model.getId()));

        return updateOrCreate(model, act);
    }

    @Override
    public void delete(final UUID id) {
        actRepository.deleteById(id);
    }

    private void validateModel(final ActModel model) throws ValidationException {
        if (model == null) {
            throw new ValidationException("Model is null");
        }

        if (model.getInvoiceIds().isEmpty()) {
            throw new ValidationException("Invoices collection can't be null");
        }
    }

    private Act updateOrCreate(final ActModel model, final Act act) throws NotFoundException {
        act.setNumber(model.getNumber());
        act.setAcceptDate(model.getAcceptDate());
        act.setStatus(model.getStatus());

        final List<Invoice> invoices = act.getInvoices();
        for (UUID invoiceId : model.getInvoiceIds()) {
            if (invoiceId == null) {
                continue;
            }

            Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);

            if (optionalInvoice.isEmpty()) {
                throw new NotFoundException(Act.class, invoiceId);
            }
            Invoice invoice = optionalInvoice.get();
            invoice.setAct(act);
            invoices.add(invoice);
        }

        return actRepository.save(act);
    }
}
