package org.pes.onecemulator.view.accountingentryadmin.dialog.edit.presenter;

import org.pes.onecemulator.model.AccountingEntryModel;

import java.util.List;

public interface IAccountingEntryEditPresenter {

    void attachView(IAccountingEntryEditDialog view);

    List<String> getExpenseRequestList();

    void onClickSaveButton(AccountingEntryModel accountingEntryModel);

    void onClickCancelButton();
}
