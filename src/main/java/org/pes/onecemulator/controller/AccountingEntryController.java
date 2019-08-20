package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.internal.AccountingEntryModel;
import org.pes.onecemulator.service.AccountingEntryService;
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
@RequestMapping("api/entry")
public class AccountingEntryController {

    private final AccountingEntryService accountingEntryService;

    @Autowired
    public AccountingEntryController(final AccountingEntryService accountingEntryService) {
        this.accountingEntryService = accountingEntryService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody AccountingEntryModel getById(@PathVariable final UUID id) throws NotFoundException {
        return accountingEntryService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<AccountingEntryModel> list() {
        return accountingEntryService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody AccountingEntryModel create(@RequestBody final AccountingEntryModel model) throws Exception {
        return accountingEntryService.create(model);
    }

    @PostMapping(value = "/update")
    public @ResponseBody AccountingEntryModel update(@RequestBody final AccountingEntryModel model) throws Exception {
        return accountingEntryService.update(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable final UUID id) {
        accountingEntryService.delete(id);
    }
}
