package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.util.List;
import java.util.UUID;

public interface AccountingEntryService {

    AccountingEntryModel getById(UUID id) throws NotFoundException;

    List<AccountingEntryModel> list();

    AccountingEntryModel create(AccountingEntryModel model) throws Exception;

    AccountingEntryModel update(AccountingEntryModel model) throws Exception;

    void delete(UUID id);
}
