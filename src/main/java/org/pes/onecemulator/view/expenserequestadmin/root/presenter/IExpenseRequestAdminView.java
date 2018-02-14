package org.pes.onecemulator.view.expenserequestadmin.root.presenter;

import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;

public interface IExpenseRequestAdminView {

    void bindingGridData(List<ExpenseRequestModel> expenseRequestModelList);

    void doFilterBySearchText();

    void toStateOfOnlyCanAdd();

    void toStateOfCanAll();

    void toStateOfCanAddAndDelete();

    void launchExpenseRequestAddDialog();

    void launchExpenseRequestEditDialog();

    void launchDeleteExpenseRequestConfirmDialog();

    List<ExpenseRequestModel> allGridSelections();

    ExpenseRequestModel gridSelection();
}
