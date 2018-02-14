package org.pes.onecemulator.view.expenserequestadmin.dialog.edit.presenter;

import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;

public interface IExpenseRequestEditPresenter {

    void attachView(IExpenseRequestEditDialog view);

    List<String> getSourceList();

    void onClickSaveButton(ExpenseRequestModel expenseRequestModel);

    void onClickCancelButton();
}
