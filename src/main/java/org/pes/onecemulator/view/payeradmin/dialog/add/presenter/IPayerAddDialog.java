package org.pes.onecemulator.view.payeradmin.dialog.add.presenter;

public interface IPayerAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnPayerAdminView();
}
