package org.pes.onecemulator.view.invoiceadmin.dialog.add.presenter;

import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public interface IInvoiceAddPresenter {

    void attachView(IInvoiceAddDialog view);

    List<String> getSourceList();

    List<String> getPayerListBySource(String source);

    void onClickSaveButton(InvoiceModel invoiceModel);

    void onClickCancelButton();
}
