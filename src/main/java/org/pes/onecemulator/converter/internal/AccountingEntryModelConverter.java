package org.pes.onecemulator.converter.internal;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.util.Optional;

public class AccountingEntryModelConverter implements Converter<AccountingEntry, AccountingEntryModel> {

    @Override
    public AccountingEntryModel convert(final AccountingEntry entity) {
        final AccountingEntryModel model = new AccountingEntryModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDate(entity.getDate());
        model.setDocumentName(entity.getDocumentName());
        model.setExpenseNumber(Optional.ofNullable(entity.getExpenseRequest())
                .map(ExpenseRequest::getNumber)
                .orElse(null));
        model.setSum(entity.getSum());

        return model;
    }
}
