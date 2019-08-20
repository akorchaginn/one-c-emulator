package org.pes.onecemulator.ui.view.sourceadmin.dialog.add;

public interface ISourceAddDialog {

    boolean hasValidationErrors();

    void showValidationErrorMessages();

    void hideErrorMessages();

    void returnSourceAdminView();
}
