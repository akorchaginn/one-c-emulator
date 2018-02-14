package org.pes.onecemulator.view.sourceadmin.dialog.add.presenter;

public interface ISourceAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnSourceAdminView();
}
