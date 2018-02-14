package org.pes.onecemulator.view.accountingentryadmin.dialog.edit.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.accountingentryadmin.dialog.edit.presenter.IAccountingEntryEditDialog;
import org.pes.onecemulator.view.accountingentryadmin.dialog.edit.presenter.IAccountingEntryEditPresenter;
import org.pes.onecemulator.view.accountingentryadmin.root.presenter.IAccountingEntryAdminView;
import org.pes.onecemulator.view.accountingentryadmin.root.view.AccountingEntryAdminView;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = AccountingEntryEditDialog.VIEW_NAME)
public class AccountingEntryEditDialog extends FormDialog implements View, IAccountingEntryEditDialog {

    public static final String VIEW_NAME = "AccountingEntryEditDialog";

    private AccountingEntryEditForm form;

    private final IAccountingEntryEditPresenter presenter;

    @Autowired
    public AccountingEntryEditDialog(IAccountingEntryEditPresenter presenter) {
        super("Редактирование проводки");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        IAccountingEntryAdminView accountingEntryView = (IAccountingEntryAdminView) event.getOldView();
        this.form = new AccountingEntryEditForm(accountingEntryView.gridSelection(), presenter.getExpenseRequestList());
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
    public void returnAccountingEntryAdminView() {
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(AccountingEntryAdminView.VIEW_NAME);
    }
}
