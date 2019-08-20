package org.pes.onecemulator.ui.view.expenserequestadmin.root.presenter;

import org.pes.onecemulator.model.internal.ExpenseRequestModel;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.add.IExpenseRequestAddDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.delete.IDeleteExpenseRequestConfirmDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.edit.IExpenseRequestEditDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.root.view.IExpenseRequestAdminView;

import java.io.Serializable;
import java.util.List;

public interface IExpenseRequestAdminPresenter extends Serializable {

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
