package org.pes.onecemulator.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pes.onecemulator.converter.InvoiceConverter;
import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.model.SimpleJsonResult;
import org.pes.onecemulator.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    private static final Object OK = "OK";

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceConverter converter;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody SimpleJsonResult getById(@PathVariable UUID id) {
        try {
            InvoiceDto dto = invoiceService.getIById(id);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody SimpleJsonResult list() {
        try {
            return new SimpleJsonResult(converter.convertToModel(invoiceService.list()));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/create")
    public @ResponseBody SimpleJsonResult create(@RequestBody InvoiceModel model) {
        try {
            InvoiceDto dto = converter.convertToDto(model);
            dto = invoiceService.create(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/update")
    public @ResponseBody SimpleJsonResult update(@RequestBody InvoiceModel model) {
        try {
            InvoiceDto dto = converter.convertToDto(model);
            dto = invoiceService.update(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @GetMapping(value = "/delete/{id}")
    public SimpleJsonResult delete(@PathVariable UUID id) {
        try {
            invoiceService.delete(id);
            return new SimpleJsonResult(OK);
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
