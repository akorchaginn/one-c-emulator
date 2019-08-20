package org.pes.onecemulator.facade.impl;

import org.pes.onecemulator.converter.internal.AccountingEntryModelConverter;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.facade.AccountingEntryFacade;
import org.pes.onecemulator.model.internal.AccountingEntryModel;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.CrmRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountingEntryFacadeImpl implements AccountingEntryFacade {

    private static final AccountingEntryModelConverter ACCOUNTING_ENTRY_MODEL_CONVERTER = new AccountingEntryModelConverter();

    private final AccountingEntryService accountingEntryService;

    private final CrmRestClientService crmRestClientService;

    @Autowired
    public AccountingEntryFacadeImpl(final AccountingEntryService accountingEntryService,
                                     final CrmRestClientService crmRestClientService) {
        this.accountingEntryService = accountingEntryService;
        this.crmRestClientService = crmRestClientService;
    }


    @Override
    public List<AccountingEntryModel> list() {
        return accountingEntryService.list().stream()
                .map(ACCOUNTING_ENTRY_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public AccountingEntryModel create(final AccountingEntryModel model) throws Exception {
        final AccountingEntry accountingEntry = accountingEntryService.create(model);

        crmRestClientService.sendExpenseRequest(accountingEntry);

        return ACCOUNTING_ENTRY_MODEL_CONVERTER.convert(accountingEntry);
    }

    @Override
    public AccountingEntryModel update(final AccountingEntryModel model) throws Exception {
        final AccountingEntry accountingEntry = accountingEntryService.update(model);

        crmRestClientService.sendExpenseRequest(accountingEntry);

        return ACCOUNTING_ENTRY_MODEL_CONVERTER.convert(accountingEntry);
    }

    @Override
    public void delete(final UUID id) {
        accountingEntryService.delete(id);
    }
}
