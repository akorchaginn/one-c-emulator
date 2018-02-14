package org.pes.onecemulator.view.invoiceadmin.dialog.edit.presenter;

public interface IInvoiceEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnSourceAdminView();
}
