package org.pes.onecemulator.view.invoiceadmin.dialog.add.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.add.presenter.IInvoiceAddDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.add.presenter.IInvoiceAddPresenter;
import org.pes.onecemulator.view.invoiceadmin.root.view.InvoiceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = InvoiceAddDialog.VIEW_NAME)
public class InvoiceAddDialog extends FormDialog implements View, IInvoiceAddDialog {

    public static final String VIEW_NAME = "InvoiceAddDialog";

    private InvoiceAddForm form;

    private final IInvoiceAddPresenter presenter;

    @Autowired
    public InvoiceAddDialog(IInvoiceAddPresenter presenter) {
        super("Добавление счета");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.form = new InvoiceAddForm(presenter.getSourceList());
        setForm(form);
        form.invoiceSource.addValueChangeListener(e -> {
            if (e.getValue() != null && !e.getValue().isEmpty()) {
                form.invoicePayer.setItems(presenter.getPayerListBySource(e.getValue()));
                form.invoicePayer.setReadOnly(false);
            } else {
                form.invoicePayer.clear();
                form.invoicePayer.setReadOnly(true);
            }
        });
        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> presenter.onClickCancelButton());
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
