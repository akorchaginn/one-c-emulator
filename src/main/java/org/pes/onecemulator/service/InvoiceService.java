package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    Invoice getById(UUID id) throws NotFoundException;

    List<Invoice> list();

    Invoice create(InvoiceModel model) throws NotFoundException, ValidationException;

    Invoice update(InvoiceModel model) throws NotFoundException, ValidationException;

    void delete(UUID id);
}
