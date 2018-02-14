package org.pes.onecemulator.view.accountingentryadmin.dialog.add.presenter;

import org.pes.onecemulator.model.AccountingEntryModel;

import java.util.List;

public interface IAccountingEntryAddPresenter {

    void attachView(IAccountingEntryAddDialog view);

    List<String> getExpenseRequestList();

    void onClickSaveButton(AccountingEntryModel accountingEntryModel);

    void onClickCancelButton();
}
