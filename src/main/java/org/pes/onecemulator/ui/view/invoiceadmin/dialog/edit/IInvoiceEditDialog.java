package org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit;

public interface IInvoiceEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnInvoiceAdminView();
}
