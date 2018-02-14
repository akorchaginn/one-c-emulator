package org.pes.onecemulator.view.accountingentryadmin.dialog.delete.presenter;

import org.pes.onecemulator.model.AccountingEntryModel;

import java.util.List;

public interface IDeleteAccountingEntryConfirmPresenter {

    void attachView(IDeleteAccountingEntryConfirmDialog view);

    void onClickOkButton(List<AccountingEntryModel> accountingEntryModelList);

    void onClickCancelButton();
}
