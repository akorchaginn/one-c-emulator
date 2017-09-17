package org.pes.onecemulator.service.repository;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.service.api.exception.DeleteEntityException;

import java.util.List;
import java.util.UUID;

public interface AccountingEntryRepositoryService {
    AccountingEntry findById(UUID id);
    List<AccountingEntry> findAll();
    AccountingEntry create(AccountingEntry accountingEntry) throws Exception;
    AccountingEntry update(AccountingEntry accountingEntry) throws Exception;
    AccountingEntry deleteLogic(UUID id) throws DeleteEntityException;
}
