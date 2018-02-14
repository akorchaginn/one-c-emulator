package org.pes.onecemulator.service;

import org.pes.onecemulator.model.AccountingEntryModel;

import java.util.List;
import java.util.UUID;

public interface AccountingEntryService {

    AccountingEntryModel getById(UUID id);

    List<AccountingEntryModel> list();

    AccountingEntryModel create(AccountingEntryModel model);

    AccountingEntryModel update(AccountingEntryModel model);

    void delete(UUID id);
}
