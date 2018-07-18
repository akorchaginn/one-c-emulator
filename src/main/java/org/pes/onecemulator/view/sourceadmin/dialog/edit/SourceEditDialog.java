package org.pes.onecemulator.view.sourceadmin.dialog.edit;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.view.fundamentals.notification.WarningNotification;
import org.pes.onecemulator.view.sourceadmin.root.presenter.ISourceAdminPresenter;
import org.pes.onecemulator.view.sourceadmin.root.view.ISourceAdminView;
import org.pes.onecemulator.view.sourceadmin.root.view.SourceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = SourceEditDialog.VIEW_NAME)
public class SourceEditDialog extends FormDialog implements View, ISourceEditDialog {

    public final static String VIEW_NAME = "SourceEditDialog";

    private SourceEditForm form;

    private final ISourceAdminPresenter presenter;

    @Autowired
    public SourceEditDialog(ISourceAdminPresenter presenter) {
        super("Редактирование базы 1С");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        ISourceAdminView sourceView = (ISourceAdminView) event.getOldView();
        this.form = new SourceEditForm(sourceView.gridSelection());
        setForm(form);

        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> returnSourceAdminView());
    }

    @Override
    public boolean hasChangesInForm() {
        return form.hasChanges();
    }

    @Override
    public void showNoChangeErrorMessage() {
        WarningNotification.show("Нет изменений.");
}

    @Override
    public boolean hasValidationErrors() {
        return form.hasValidationErrors();
    }

    @Override
    public void showValidationErrorMessages() {
        form.validate();
        setErrorMessageAsHtml(form.errorMessagesAsHtml());
        setVisibleOfErrorDisplay(true);
    }

    @Override
    public void hideErrorMessages() {
        setVisibleOfErrorDisplay(false);
    }

    @Override
    public void returnSourceAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(SourceAdminView.VIEW_NAME);
    }
}
