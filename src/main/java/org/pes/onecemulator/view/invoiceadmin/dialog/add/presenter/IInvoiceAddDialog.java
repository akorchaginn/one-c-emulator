package org.pes.onecemulator.view.invoiceadmin.dialog.add.presenter;

public interface IInvoiceAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnInvoiceAdminView();
}
