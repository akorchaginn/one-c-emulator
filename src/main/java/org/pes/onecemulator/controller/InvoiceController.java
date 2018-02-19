package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody InvoiceModel getById(@PathVariable UUID id) throws NotFoundException {
        return invoiceService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<InvoiceModel> list() {
        return invoiceService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody InvoiceModel create(@RequestBody InvoiceModel model) throws Exception {
        return invoiceService.create(model);
    }

    @PostMapping(value = "/update")
    public @ResponseBody InvoiceModel update(@RequestBody InvoiceModel model) throws Exception {
        return invoiceService.update(model);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable UUID id) {
        invoiceService.delete(id);
    }
}
