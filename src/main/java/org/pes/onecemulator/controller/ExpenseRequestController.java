package org.pes.onecemulator.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pes.onecemulator.converter.ExpenseRequestConverter;
import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.model.ERequestModel;
import org.pes.onecemulator.model.SimpleJsonResult;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/expense-request")
public class ExpenseRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestController.class);

    private static final Object OK = "OK";

    @Autowired
    private ExpenseRequestService expenseRequestService;

    @Autowired
    private ExpenseRequestConverter converter;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody SimpleJsonResult getById(@PathVariable UUID id) {
        try {
            ExpenseRequestDto dto = expenseRequestService.getById(id);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }

    }

    @GetMapping(value = "/list")
    public @ResponseBody SimpleJsonResult list() {
        try {
            return new SimpleJsonResult(converter.convertToModel(expenseRequestService.list()));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/create")
    public @ResponseBody SimpleJsonResult create(@RequestBody ERequestModel model) {
        try {
            ExpenseRequestDto dto = converter.convertToDto(model);
            dto = expenseRequestService.create(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/update")
    public @ResponseBody SimpleJsonResult update(@RequestBody ERequestModel model) {
        try {
            ExpenseRequestDto dto = converter.convertToDto(model);
            dto = expenseRequestService.update(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @GetMapping(value = "/delete/{id}")
    public SimpleJsonResult delete(@PathVariable(value = "id") UUID id) {
        try {
            expenseRequestService.delete(id);
            return new SimpleJsonResult(OK);
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
