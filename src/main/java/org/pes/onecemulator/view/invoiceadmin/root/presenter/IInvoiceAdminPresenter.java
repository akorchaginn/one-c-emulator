package org.pes.onecemulator.view.invoiceadmin.root.presenter;

import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.view.invoiceadmin.dialog.add.IInvoiceAddDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.delete.IDeleteInvoiceConfirmDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.edit.IInvoiceEditDialog;
import org.pes.onecemulator.view.invoiceadmin.root.view.IInvoiceAdminView;

import java.util.List;

public interface IInvoiceAdminPresenter {

    void attachView(IInvoiceAdminView view);

    void attachView(IInvoiceAddDialog view);

    void attachView(IInvoiceEditDialog view);

    void attachView(IDeleteInvoiceConfirmDialog view);

    void onClickOkButton(List<InvoiceModel> invoiceModelList);

    List<String> getSourceList();

    List<String> getPayerListBySource(String source);

    void onClickSaveButton(InvoiceModel invoiceModel);

    void loadSourceList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
