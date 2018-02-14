package org.pes.onecemulator.view.expenserequestadmin.dialog.add.presenter;

public interface IExpenseRequestAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnExpenseRequestAdminView();
}
