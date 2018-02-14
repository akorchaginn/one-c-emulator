package org.pes.onecemulator.view.sourceadmin.dialog.edit.presenter;

public interface ISourceEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnSourceAdminView();
}
