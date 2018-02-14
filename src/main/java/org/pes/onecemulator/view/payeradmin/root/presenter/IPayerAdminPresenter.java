package org.pes.onecemulator.view.payeradmin.root.presenter;

public interface IPayerAdminPresenter {

    void attachView(IPayerAdminView view);

    void loadPayers();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
