package org.pes.onecemulator.view.expenserequestadmin.dialog.delete.presenter;

import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;

public interface IDeleteExpenseRequestConfirmPresenter {

    void attachView(IDeleteExpenseRequestConfirmDialog view);

    void onClickOkButton(List<ExpenseRequestModel> expenseRequestModelList);

    void onClickCancelButton();
}
