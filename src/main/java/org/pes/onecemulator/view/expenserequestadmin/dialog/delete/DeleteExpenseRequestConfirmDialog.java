package org.pes.onecemulator.view.expenserequestadmin.dialog.delete;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.expenserequestadmin.root.presenter.IExpenseRequestAdminPresenter;
import org.pes.onecemulator.view.expenserequestadmin.root.view.IExpenseRequestAdminView;
import org.pes.onecemulator.view.expenserequestadmin.root.view.ExpenseRequestAdminView;
import org.pes.onecemulator.view.fundamentals.dialog.confirm.ConfirmDialog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = DeleteExpenseRequestConfirmDialog.VIEW_NAME)
public class DeleteExpenseRequestConfirmDialog  extends ConfirmDialog implements View, IDeleteExpenseRequestConfirmDialog{

    public final static String VIEW_NAME = "DeleteExpenseRequestConfirmDialog";

    private final IExpenseRequestAdminPresenter presenter;

    @Autowired
    public DeleteExpenseRequestConfirmDialog(IExpenseRequestAdminPresenter presenter) {
        super("Вы действительно хотите удалить заявку на расход?");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        IExpenseRequestAdminView expenseRequestView = (IExpenseRequestAdminView) event.getOldView();
        addClickListenerToOkButton(e -> presenter.onClickOkButton(expenseRequestView.allGridSelections()));
        addClickListenerToCancelButton(e -> returnExpenseRequestAdminView());
    }

    @Override
    public void returnExpenseRequestAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(ExpenseRequestAdminView.VIEW_NAME);
    }
}
