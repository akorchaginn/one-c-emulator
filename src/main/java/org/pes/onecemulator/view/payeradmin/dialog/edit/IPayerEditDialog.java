package org.pes.onecemulator.view.payeradmin.dialog.edit;

public interface IPayerEditDialog {

    boolean hasChangesInForm();

    void showNoChangeErrorMessage();

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnPayerAdminView();
}
