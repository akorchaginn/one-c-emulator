package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.add;

public interface IAccountingEntryAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnAccountingEntryAdminView();
}
