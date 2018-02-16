package org.pes.onecemulator.view.accountingentryadmin.dialog.add;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.accountingentryadmin.root.presenter.IAccountingEntryAdminPresenter;
import org.pes.onecemulator.view.accountingentryadmin.root.view.AccountingEntryAdminView;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = AccountingEntryAddDialog.VIEW_NAME)
public class AccountingEntryAddDialog extends FormDialog implements View, IAccountingEntryAddDialog {

    public static final String VIEW_NAME = "AccountingEntryAddDialog";

    private AccountingEntryAddForm form;

    private final IAccountingEntryAdminPresenter presenter;

    @Autowired
    public AccountingEntryAddDialog(IAccountingEntryAdminPresenter presenter) {
        super("Создание проводки");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.form = new AccountingEntryAddForm(presenter.getExpenseRequestList());
        setForm(form);

        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> returnAccountingEntryAdminView());
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
    public void returnAccountingEntryAdminView() {
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(AccountingEntryAdminView.VIEW_NAME);
    }
}
