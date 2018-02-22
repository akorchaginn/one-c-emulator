package org.pes.onecemulator.view.accountingentryadmin.root.presenter;

import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.view.accountingentryadmin.dialog.add.IAccountingEntryAddDialog;
import org.pes.onecemulator.view.accountingentryadmin.dialog.delete.IDeleteAccountingEntryConfirmDialog;
import org.pes.onecemulator.view.accountingentryadmin.dialog.edit.IAccountingEntryEditDialog;
import org.pes.onecemulator.view.accountingentryadmin.root.view.IAccountingEntryAdminView;

import java.util.List;

public interface IAccountingEntryAdminPresenter {

    void attachView(IAccountingEntryAdminView view);

    void attachView(IAccountingEntryAddDialog view);

    void attachView(IAccountingEntryEditDialog view);

    void onClickSaveButton(AccountingEntryModel accountingEntryModel);

    void attachView(IDeleteAccountingEntryConfirmDialog view);

    void onClickOkButton(List<AccountingEntryModel> accountingEntryModelList);

    List<String> getExpenseRequestList();

    void loadAccountingEntryList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
