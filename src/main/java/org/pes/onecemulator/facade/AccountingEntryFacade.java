package org.pes.onecemulator.facade;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.util.List;
import java.util.UUID;

public interface AccountingEntryFacade {

    List<AccountingEntryModel> list();

    AccountingEntryModel create(AccountingEntryModel model) throws Exception;

    AccountingEntryModel update(AccountingEntryModel model) throws Exception;

    void delete(UUID id);
}
