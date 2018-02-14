package org.pes.onecemulator.view.sourceadmin.dialog.edit.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.edit.presenter.ISourceEditDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.edit.presenter.ISourceEditPresenter;
import org.pes.onecemulator.view.sourceadmin.root.presenter.ISourceAdminView;
import org.pes.onecemulator.view.sourceadmin.root.view.SourceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = SourceEditDialog.VIEW_NAME)
public class SourceEditDialog extends FormDialog implements View, ISourceEditDialog {

    public final static String VIEW_NAME = "SourceEditDialog";

    private SourceEditForm form;

    private final ISourceEditPresenter presenter;

    @Autowired
    public SourceEditDialog(ISourceEditPresenter presenter) {
        super("Редактирование базы 1С");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // initialize from data of transition source view
        ISourceAdminView sourceView = (ISourceAdminView) event.getOldView();
        this.form = new SourceEditForm(sourceView.gridSelection());
        setForm(form);

        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> presenter.onClickCancelButton());
    }

    @Override
    public boolean hasChangesInForm() {
        return form.hasChanges();
    }

    @Override
    public void showNoChangeErrorMessage() {
        Notification.show("Нет изменений.", Notification.Type.ERROR_MESSAGE);
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
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(SourceAdminView.VIEW_NAME);
    }
}
