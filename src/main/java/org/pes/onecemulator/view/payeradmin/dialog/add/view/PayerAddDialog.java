package org.pes.onecemulator.view.payeradmin.dialog.add.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.view.payeradmin.dialog.add.presenter.IPayerAddDialog;
import org.pes.onecemulator.view.payeradmin.dialog.add.presenter.IPayerAddPresenter;
import org.pes.onecemulator.view.payeradmin.root.view.PayerAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = PayerAddDialog.VIEW_NAME)
public class PayerAddDialog extends FormDialog implements View, IPayerAddDialog {

    public static final String VIEW_NAME = "PayerAddDialog";

    private PayerAddForm form;

    private final IPayerAddPresenter presenter;

    @Autowired
    public PayerAddDialog(IPayerAddPresenter presenter) {
        super("Добавление плательщика");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.form = new PayerAddForm(presenter.getSourceList());
        setForm(form);

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
    public void returnPayerAdminView() {
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(PayerAdminView.VIEW_NAME);
    }
}
