package org.pes.onecemulator.service;

import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    InvoiceModel getById(UUID id);

    InvoiceModel getByIdAndSource(UUID id, String source);

    InvoiceModel getByExternalId(String externalId);

    InvoiceModel getByExternalIdAndSource(String externalId, String source);

    List<InvoiceModel> list();

    InvoiceModel create(InvoiceModel model);

    InvoiceModel update(InvoiceModel model);

    void delete(UUID id);
}
