package org.pes.onecemulator.view.expenserequestadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class ExpenseRequestAdminPresenter implements IExpenseRequestAdminPresenter {

    private IExpenseRequestAdminView view;

    private final ExpenseRequestService expenseRequestService;

    @Autowired
    public ExpenseRequestAdminPresenter(ExpenseRequestService expenseRequestService) {
        this.expenseRequestService = expenseRequestService;
    }

    @Override
    public void attachView(IExpenseRequestAdminView view) {
        this.view = view;
    }

    @Override
    public void loadExpenseRequestList() {
        view.bindingGridData(expenseRequestService.list());
    }

    @Override
    public void onClickSearchButton() {
        view.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        view.launchExpenseRequestAddDialog();
    }

    @Override
    public void onClickEditButton() {
        view.launchExpenseRequestEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        view.launchDeleteExpenseRequestConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<ExpenseRequestModel> selections = view.allGridSelections();
        if (selections.isEmpty()) view.toStateOfOnlyCanAdd();
        if (selections.size() == 1) view.toStateOfCanAll();
        if (selections.size() > 1) view.toStateOfCanAddAndDelete();
    }
}
