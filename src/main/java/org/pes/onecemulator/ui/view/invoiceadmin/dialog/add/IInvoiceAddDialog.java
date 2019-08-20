package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

public interface IInvoiceAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnInvoiceAdminView();
}
