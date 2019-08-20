package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.edit;

public interface IAccountingEntryEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnAccountingEntryAdminView();
}
