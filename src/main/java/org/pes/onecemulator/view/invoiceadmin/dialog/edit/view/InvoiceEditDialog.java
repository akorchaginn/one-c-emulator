package org.pes.onecemulator.view.invoiceadmin.dialog.edit.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.edit.presenter.IInvoiceEditDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.edit.presenter.IInvoiceEditPresenter;
import org.pes.onecemulator.view.invoiceadmin.root.presenter.IInvoiceAdminView;
import org.pes.onecemulator.view.invoiceadmin.root.view.InvoiceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = InvoiceEditDialog.VIEW_NAME)
public class InvoiceEditDialog extends FormDialog implements View, IInvoiceEditDialog {

    public final static String VIEW_NAME = "InvoiceEditDialog";

    private InvoiceEditForm form;

    private final IInvoiceEditPresenter presenter;

    @Autowired
    public InvoiceEditDialog(IInvoiceEditPresenter presenter) {
        super("Редактирование счета");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // initialize from data of transition source view
        IInvoiceAdminView invoiceView = (IInvoiceAdminView) event.getOldView();
        this.form = new InvoiceEditForm(invoiceView.gridSelection(), presenter.getSources(), presenter.getPayers());
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
    public void returnInvoiceAdminView() {
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(InvoiceAdminView.VIEW_NAME);
    }
}
