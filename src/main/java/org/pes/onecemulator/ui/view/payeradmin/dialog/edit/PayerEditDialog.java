package org.pes.onecemulator.ui.view.payeradmin.dialog.edit;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.ui.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.ui.view.fundamentals.notification.WarningNotification;
import org.pes.onecemulator.ui.view.payeradmin.root.presenter.IPayerAdminPresenter;
import org.pes.onecemulator.ui.view.payeradmin.root.view.IPayerAdminView;
import org.pes.onecemulator.ui.view.payeradmin.root.view.PayerAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = PayerEditDialog.VIEW_NAME)
public class PayerEditDialog extends FormDialog implements View, IPayerEditDialog {

    public final static String VIEW_NAME = "PayerEditDialog";

    private PayerEditForm form;

    private final IPayerAdminPresenter presenter;

    @Autowired
    public PayerEditDialog(IPayerAdminPresenter presenter) {
        super("Редактирование плательщика");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        IPayerAdminView sourceView = (IPayerAdminView) event.getOldView();
        this.form = new PayerEditForm(sourceView.gridSelection(), presenter.getSourceList());
        setForm(form);

        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> returnPayerAdminView());
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
    public void returnPayerAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(PayerAdminView.VIEW_NAME);
    }
}
