package org.pes.onecemulator.view.expenserequestadmin.dialog.delete.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class DeleteExpenseRequestConfirmPresenter implements IDeleteExpenseRequestConfirmPresenter {

    private IDeleteExpenseRequestConfirmDialog view;

    private final ExpenseRequestService expenseRequestService;

    @Autowired
    public DeleteExpenseRequestConfirmPresenter(ExpenseRequestService expenseRequestService) {
        this.expenseRequestService = expenseRequestService;
    }

    @Override
    public void attachView(IDeleteExpenseRequestConfirmDialog view) {
        this.view = view;
    }

    @Override
    public void onClickOkButton(List<ExpenseRequestModel> expenseRequestModelList) {
        expenseRequestModelList.forEach(payer -> expenseRequestService.delete(payer.getId()));
        view.returnExpenseRequestAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnExpenseRequestAdminView();
    }
}
