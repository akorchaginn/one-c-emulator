package org.pes.onecemulator.facade;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.util.List;
import java.util.UUID;

public interface InvoiceFacade {

    List<InvoiceModel> list();

    InvoiceModel create(InvoiceModel model) throws NotFoundException, ValidationException;

    InvoiceModel update(InvoiceModel model) throws NotFoundException, ValidationException;

    void delete(UUID id);
}
