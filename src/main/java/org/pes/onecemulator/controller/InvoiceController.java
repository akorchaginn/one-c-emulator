package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
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

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody InvoiceModel getById(@PathVariable final UUID id) throws NotFoundException {
        return invoiceService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<InvoiceModel> list() {
        return invoiceService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody InvoiceModel create(@RequestBody final InvoiceModel model) throws Exception {
        return invoiceService.create(model);
    }

    @PostMapping(value = "/update")
    public @ResponseBody InvoiceModel update(@RequestBody final InvoiceModel model) throws Exception {
        return invoiceService.update(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable final UUID id) {
        invoiceService.delete(id);
    }
}
