package org.pes.onecemulator.view.expenserequestadmin.dialog.edit;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.expenserequestadmin.root.presenter.IExpenseRequestAdminPresenter;
import org.pes.onecemulator.view.expenserequestadmin.root.view.ExpenseRequestAdminView;
import org.pes.onecemulator.view.expenserequestadmin.root.view.IExpenseRequestAdminView;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.pes.onecemulator.view.fundamentals.notification.WarningNotification;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = ExpenseRequestEditDialog.VIEW_NAME)
public class ExpenseRequestEditDialog extends FormDialog implements View, IExpenseRequestEditDialog {

    public final static String VIEW_NAME = "ExpenseRequestEditDialog";

    private ExpenseRequestEditForm form;

    private final IExpenseRequestAdminPresenter presenter;

    @Autowired
    public ExpenseRequestEditDialog(IExpenseRequestAdminPresenter presenter) {
        super("Редактирование заявки на расход");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // initialize from data of transition source view
        IExpenseRequestAdminView expenseRequestView = (IExpenseRequestAdminView) event.getOldView();
        this.form = new ExpenseRequestEditForm(expenseRequestView.gridSelection(), presenter.getSourceList());
        setForm(form);

        addClickEvenListenerToSaveButton(e -> presenter.onClickSaveButton(form.valueAsObject()));
        addClickEventListenerToCancelButton(e -> returnExpenseRequestAdminView());
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
    public void returnExpenseRequestAdminView() {
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(ExpenseRequestAdminView.VIEW_NAME);
    }
}
