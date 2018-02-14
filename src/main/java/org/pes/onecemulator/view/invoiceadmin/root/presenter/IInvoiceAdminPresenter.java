package org.pes.onecemulator.view.invoiceadmin.root.presenter;

public interface IInvoiceAdminPresenter {

    void attachView(IInvoiceAdminView view);

    void loadSources();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
