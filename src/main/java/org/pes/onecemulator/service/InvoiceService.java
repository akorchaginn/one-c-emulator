package org.pes.onecemulator.service;

import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    InvoiceModel getById(UUID id);

    List<InvoiceModel> list();

    InvoiceModel create(InvoiceModel model);

    InvoiceModel update(InvoiceModel model);

    void delete(UUID id);
}
