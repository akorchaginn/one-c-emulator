package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.edit;

public interface IExpenseRequestEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnExpenseRequestAdminView();
}
