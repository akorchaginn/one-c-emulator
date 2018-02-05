package org.pes.onecemulator.controller;

import org.pes.onecemulator.model.AEntryModel;
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
    public @ResponseBody AEntryModel getById(@PathVariable UUID id) {
        return accountingEntryService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<AEntryModel> list() {
        return accountingEntryService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody AEntryModel create(@RequestBody AEntryModel model) {
        return accountingEntryService.create(model);
    }

    @PostMapping(value = "/update")
    public @ResponseBody AEntryModel update(@RequestBody AEntryModel model) {
        return accountingEntryService.update(model);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable UUID id) {
        accountingEntryService.delete(id);
    }
}
