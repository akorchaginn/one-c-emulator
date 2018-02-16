package org.pes.onecemulator.view.accountingentryadmin.dialog.edit;

public interface IAccountingEntryEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnAccountingEntryAdminView();
}
