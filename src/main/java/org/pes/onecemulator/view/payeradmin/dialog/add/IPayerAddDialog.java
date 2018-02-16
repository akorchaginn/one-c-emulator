package org.pes.onecemulator.view.payeradmin.dialog.add;

public interface IPayerAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnPayerAdminView();
}
