package org.pes.onecemulator.view.expenserequestadmin.dialog.add.presenter;

import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;

public interface IExpenseRequestAddPresenter {

    void attachView(IExpenseRequestAddDialog view);

    List<String> getSourceList();

    void onClickSaveButton(ExpenseRequestModel expenseRequestModel);

    void onClickCancelButton();
}
