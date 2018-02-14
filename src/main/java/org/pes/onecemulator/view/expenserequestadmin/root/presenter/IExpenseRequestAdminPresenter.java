package org.pes.onecemulator.view.expenserequestadmin.root.presenter;

public interface IExpenseRequestAdminPresenter {

    void attachView(IExpenseRequestAdminView view);

    void loadExpenseRequestList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
