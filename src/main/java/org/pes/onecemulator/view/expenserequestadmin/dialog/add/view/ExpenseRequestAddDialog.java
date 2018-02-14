package org.pes.onecemulator.view.expenserequestadmin.dialog.add.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.expenserequestadmin.dialog.add.presenter.IExpenseRequestAddDialog;
import org.pes.onecemulator.view.expenserequestadmin.dialog.add.presenter.IExpenseRequestAddPresenter;
import org.pes.onecemulator.view.expenserequestadmin.root.view.ExpenseRequestAdminView;
import org.pes.onecemulator.view.fundamentals.dialog.form.FormDialog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = ExpenseRequestAddDialog.VIEW_NAME)
public class ExpenseRequestAddDialog extends FormDialog implements View, IExpenseRequestAddDialog {

    public static final String VIEW_NAME = "ExpenseRequestAddDialog";

    private ExpenseRequestAddForm form;

    private final IExpenseRequestAddPresenter presenter;

    @Autowired
    public ExpenseRequestAddDialog(IExpenseRequestAddPresenter presenter) {
        super("Добавление заявки на расход");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        this.form = new ExpenseRequestAddForm(presenter.getSourceList());
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
    public void returnExpenseRequestAdminView() {
        // note: getUI() return null
        close();
        UI.getCurrent().getNavigator().navigateTo(ExpenseRequestAdminView.VIEW_NAME);
    }
}
