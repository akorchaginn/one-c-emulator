package org.pes.onecemulator.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pes.onecemulator.converter.AccountingEntryConverter;
import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.model.AEntryModel;
import org.pes.onecemulator.model.SimpleJsonResult;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.CrmInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/entry")
public class AccountingEntryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingEntryController.class);

    private static final Object OK = "OK";

    @Autowired
    private AccountingEntryService accountingEntryService;

    @Autowired
    private AccountingEntryConverter converter;

    @Autowired
    private CrmInteractionService crmInteractionService;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody SimpleJsonResult getById(@PathVariable UUID id) {
        try {
            AccountingEntryDto dto = accountingEntryService.getById(id);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @GetMapping(value = "/list")
    public @ResponseBody SimpleJsonResult list() {
        try {
            return new SimpleJsonResult(converter.convertToModel(accountingEntryService.list()));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/create")
    public @ResponseBody SimpleJsonResult create(@RequestBody AEntryModel model) {
        try {
            AccountingEntryDto dto = converter.convertToDto(model);
            dto = accountingEntryService.create(dto);
            crmInteractionService.sendAccountingEntryToCrm(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @PostMapping(value = "/update")
    public @ResponseBody SimpleJsonResult update(@RequestBody AEntryModel model) {
        try {
            AccountingEntryDto dto = converter.convertToDto(model);
            dto = accountingEntryService.update(dto);
            crmInteractionService.sendAccountingEntryToCrm(dto);
            return new SimpleJsonResult(converter.convertToModel(dto));
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }

    @GetMapping(value = "/delete/{id}")
    public SimpleJsonResult delete(@PathVariable UUID id) {
        try {
            accountingEntryService.delete(id);
            return new SimpleJsonResult(OK);
        } catch (Exception e) {
            return new SimpleJsonResult(ExceptionUtils.getRootCauseMessage(e));
        }
    }
}
