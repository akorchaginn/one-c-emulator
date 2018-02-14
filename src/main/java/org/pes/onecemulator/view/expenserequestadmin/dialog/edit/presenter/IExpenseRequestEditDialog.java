package org.pes.onecemulator.view.expenserequestadmin.dialog.edit.presenter;

public interface IExpenseRequestEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnExpenseRequestAdminView();
}
