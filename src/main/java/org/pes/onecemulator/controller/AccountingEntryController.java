package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.service.AccountingEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/entry")
public class AccountingEntryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingEntryController.class);

    @Autowired
    private AccountingEntryService accountingEntryService;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody
    AccountingEntryModel getById(@PathVariable UUID id) throws NotFoundException {
        return accountingEntryService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<AccountingEntryModel> list() {
        return accountingEntryService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody
    AccountingEntryModel create(@RequestBody AccountingEntryModel model) throws Exception {
        return accountingEntryService.create(model);
    }

    @PostMapping(value = "/update")
    public @ResponseBody
    AccountingEntryModel update(@RequestBody AccountingEntryModel model) throws Exception {
        return accountingEntryService.update(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable UUID id) {
        accountingEntryService.delete(id);
    }
}
