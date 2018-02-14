package org.pes.onecemulator.view.invoiceadmin.dialog.add.presenter;

import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public interface IInvoiceAddPresenter {

    void attachView(IInvoiceAddDialog view);

    List<String> getSources();

    List<String> getPayers();

    void onClickSaveButton(InvoiceModel invoiceModel);

    void onClickCancelButton();
}
