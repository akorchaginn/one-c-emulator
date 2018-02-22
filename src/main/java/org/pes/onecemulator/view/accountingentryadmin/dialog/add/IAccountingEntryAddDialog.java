package org.pes.onecemulator.view.accountingentryadmin.dialog.add;

public interface IAccountingEntryAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnAccountingEntryAdminView();
}
