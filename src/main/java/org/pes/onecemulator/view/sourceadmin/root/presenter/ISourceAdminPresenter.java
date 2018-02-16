package org.pes.onecemulator.view.sourceadmin.root.presenter;

public interface ISourceAdminPresenter {

    void attachView(ISourceAdminView view);

    void loadSourceList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
