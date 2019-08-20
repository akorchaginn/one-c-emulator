package org.pes.onecemulator.controller;

import org.pes.onecemulator.converter.internal.InvoiceModelConverter;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.InvoiceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private static final InvoiceModelConverter INVOICE_MODEL_CONVERTER = new InvoiceModelConverter();

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody InvoiceModel getById(@PathVariable final UUID id) throws NotFoundException {
        return INVOICE_MODEL_CONVERTER.convert(invoiceService.getById(id));
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<InvoiceModel> list() {
        return invoiceService.list().stream()
                .map(INVOICE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    public @ResponseBody InvoiceModel create(@RequestBody final InvoiceModel model) throws NotFoundException, ValidationException {
        return INVOICE_MODEL_CONVERTER.convert(invoiceService.create(model));
    }

    @PostMapping(value = "/update")
    public @ResponseBody InvoiceModel update(@RequestBody final InvoiceModel model) throws NotFoundException, ValidationException {
        return INVOICE_MODEL_CONVERTER.convert(invoiceService.update(model));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable final UUID id) {
        invoiceService.delete(id);
    }
}
