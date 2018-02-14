package org.pes.onecemulator.view.invoiceadmin.dialog.delete.presenter;

import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public interface IDeleteInvoiceConfirmPresenter {

    void attachView(IDeleteInvoiceConfirmDialog view);

    void onClickOkButton(List<InvoiceModel> invoiceModelList);

    void onClickCancelButton();
}
