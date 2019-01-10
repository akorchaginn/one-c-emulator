package org.pes.onecemulator.view.expenserequestadmin.root.presenter;

import org.pes.onecemulator.model.internal.ExpenseRequestModel;
import org.pes.onecemulator.view.expenserequestadmin.dialog.add.IExpenseRequestAddDialog;
import org.pes.onecemulator.view.expenserequestadmin.dialog.delete.IDeleteExpenseRequestConfirmDialog;
import org.pes.onecemulator.view.expenserequestadmin.dialog.edit.IExpenseRequestEditDialog;
import org.pes.onecemulator.view.expenserequestadmin.root.view.IExpenseRequestAdminView;

import java.util.List;

public interface IExpenseRequestAdminPresenter {

    void attachView(IExpenseRequestAdminView view);

    void attachView(IExpenseRequestAddDialog view);

    void attachView(IExpenseRequestEditDialog view);

    void onClickSaveButton(ExpenseRequestModel expenseRequestModel);

    void attachView(IDeleteExpenseRequestConfirmDialog view);

    void onClickOkButton(List<ExpenseRequestModel> expenseRequestModelList);

    List<String> getSourceList();

    void loadExpenseRequestList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
