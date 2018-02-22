package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    InvoiceModel getById(UUID id) throws NotFoundException;

    List<InvoiceModel> list();

    InvoiceModel create(InvoiceModel model) throws Exception;

    InvoiceModel update(InvoiceModel model) throws Exception;

    void delete(UUID id);
}
