package org.pes.onecemulator.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pes.onecemulator.converter.PayerConverter;
import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SimpleJsonResult;
import org.pes.onecemulator.service.PayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/payer")
public class PayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayerController.class);

    private static final Object OK = "OK";

    @Autowired
    private PayerService payerService;

    @Autowired
    private PayerConverter converter;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody SimpleJsonResult getById(@PathVariable UUID id) {
        try {
            PayerDto dto = payerService.getById(id);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody SimpleJsonResult list() {
        try {
            return new SimpleJsonResult(converter.convertToModel(payerService.list()));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/create")
    public @ResponseBody SimpleJsonResult create(@RequestBody PayerModel model) {
        try {
            PayerDto dto = converter.convertToDto(model);
            dto = payerService.create(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/create-all")
    public @ResponseBody SimpleJsonResult create(@RequestBody List<PayerModel> modelList) {
        try {
            for (PayerDto dto : converter.convertToDto(modelList)) {
                payerService.create(dto);
            }
            return new SimpleJsonResult(converter.convertToModel(payerService.list()));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/update")
    public @ResponseBody SimpleJsonResult update(@RequestBody PayerModel model) {
        try {
            PayerDto dto = converter.convertToDto(model);
            dto = payerService.update(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @GetMapping(value = "/delete/{id}")
    public SimpleJsonResult delete(@PathVariable(value = "id") UUID id) {
        try {
            payerService.delete(id);
            return new SimpleJsonResult(OK);
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
