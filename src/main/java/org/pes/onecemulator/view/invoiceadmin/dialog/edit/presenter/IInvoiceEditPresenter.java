package org.pes.onecemulator.view.invoiceadmin.dialog.edit.presenter;

import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public interface IInvoiceEditPresenter {

    void attachView(IInvoiceEditDialog view);

    List<String> getSourceList();

    List<String> getPayerListBySource(String source);

    void onClickSaveButton(InvoiceModel invoiceModel);

    void onClickCancelButton();
}
