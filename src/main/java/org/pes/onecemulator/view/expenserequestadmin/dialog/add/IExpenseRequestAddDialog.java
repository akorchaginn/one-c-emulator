package org.pes.onecemulator.view.expenserequestadmin.dialog.add;

public interface IExpenseRequestAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnExpenseRequestAdminView();
}
