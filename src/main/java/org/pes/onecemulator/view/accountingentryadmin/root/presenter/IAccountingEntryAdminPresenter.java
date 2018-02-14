package org.pes.onecemulator.view.accountingentryadmin.root.presenter;

public interface IAccountingEntryAdminPresenter {

    void attachView(IAccountingEntryAdminView view);

    void loadAccountingEntryList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
