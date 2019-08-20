package org.pes.onecemulator.facade.impl;

import org.pes.onecemulator.converter.internal.InvoiceModelConverter;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.facade.InvoiceFacade;
import org.pes.onecemulator.model.internal.InvoiceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceFacadeImpl implements InvoiceFacade {

    private static final InvoiceModelConverter INVOICE_MODEL_CONVERTER = new InvoiceModelConverter();

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceFacadeImpl(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public List<InvoiceModel> list() {
        return invoiceService.list().stream()
                .map(INVOICE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceModel create(final InvoiceModel model) throws NotFoundException, ValidationException {
        return INVOICE_MODEL_CONVERTER.convert(invoiceService.create(model));
    }

    @Override
    public InvoiceModel update(final InvoiceModel model) throws NotFoundException, ValidationException {
        return INVOICE_MODEL_CONVERTER.convert(invoiceService.create(model));
    }

    @Override
    public void delete(final UUID id) {
        invoiceService.delete(id);
    }
}
