package org.pes.onecemulator.ui.view.expenserequestadmin.root.view;

import org.pes.onecemulator.model.internal.ExpenseRequestModel;

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
