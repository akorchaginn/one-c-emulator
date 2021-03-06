package org.pes.onecemulator.controller;

import org.pes.onecemulator.converter.internal.AccountingEntryModelConverter;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.AccountingEntryModel;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.CrmRestClientService;
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
@RequestMapping("api/entry")
public class AccountingEntryController {

    private static final AccountingEntryModelConverter ACCOUNTING_ENTRY_MODEL_CONVERTER = new AccountingEntryModelConverter();

    private final AccountingEntryService accountingEntryService;

    private final CrmRestClientService crmRestClientService;

    @Autowired
    public AccountingEntryController(final AccountingEntryService accountingEntryService,
                                     final CrmRestClientService crmRestClientService) {
        this.accountingEntryService = accountingEntryService;
        this.crmRestClientService = crmRestClientService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody AccountingEntryModel getById(@PathVariable final UUID id) throws NotFoundException {
        return ACCOUNTING_ENTRY_MODEL_CONVERTER.convert(accountingEntryService.getById(id));
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<AccountingEntryModel> list() {
        return accountingEntryService.list().stream()
                .map(ACCOUNTING_ENTRY_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    public @ResponseBody AccountingEntryModel create(@RequestBody final AccountingEntryModel model) throws NotFoundException, ValidationException {
        final AccountingEntry accountingEntry = accountingEntryService.create(model);

        crmRestClientService.sendExpenseRequest(accountingEntry);

        return ACCOUNTING_ENTRY_MODEL_CONVERTER.convert(accountingEntry);
    }

    @PostMapping(value = "/update")
    public @ResponseBody AccountingEntryModel update(@RequestBody final AccountingEntryModel model) throws NotFoundException, ValidationException {
        final AccountingEntry accountingEntry = accountingEntryService.update(model);

        crmRestClientService.sendExpenseRequest(accountingEntry);

        return ACCOUNTING_ENTRY_MODEL_CONVERTER.convert(accountingEntry);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable final UUID id) {
        accountingEntryService.delete(id);
    }
}
