package org.pes.onecemulator.converter;

import org.pes.onecemulator.entity.AccountingEntry;
import org.pes.onecemulator.model.AccountingEntryModel;

public class AccountingEntryModelConverter implements Converter<AccountingEntry, AccountingEntryModel> {

    @Override
    public AccountingEntryModel convert(AccountingEntry entity) {
        final AccountingEntryModel model = new AccountingEntryModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDate(entity.getDate());
        model.setDocumentName(entity.getDocumentName());
        model.setExpenseNumber(entity.getExpenseRequest() != null
                ? entity.getExpenseRequest().getNumber()
                : null
        );
        model.setSum(entity.getSum());

        return model;
    }
}
