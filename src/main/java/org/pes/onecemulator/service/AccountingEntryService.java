package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.util.List;
import java.util.UUID;

public interface AccountingEntryService {

    AccountingEntry getById(UUID id) throws NotFoundException;

    List<AccountingEntry> list();

    AccountingEntry create(AccountingEntryModel model) throws NotFoundException, ValidationException;

    AccountingEntry update(AccountingEntryModel model) throws NotFoundException, ValidationException;

    void delete(UUID id);
}
