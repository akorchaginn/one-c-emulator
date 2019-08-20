package org.pes.onecemulator.ui.view.expenserequestadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.facade.ExpenseRequestFacade;
import org.pes.onecemulator.facade.SourceFacade;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.add.IExpenseRequestAddDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.delete.IDeleteExpenseRequestConfirmDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.edit.IExpenseRequestEditDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.root.view.IExpenseRequestAdminView;
import org.pes.onecemulator.ui.view.fundamentals.notification.ErrorNotification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class ExpenseRequestAdminPresenter implements IExpenseRequestAdminPresenter {

    private IExpenseRequestAdminView adminView;

    private IExpenseRequestAddDialog addView;

    private IExpenseRequestEditDialog editView;

    private IDeleteExpenseRequestConfirmDialog deleteView;

    private final ExpenseRequestFacade expenseRequestFacade;

    private final SourceFacade sourceFacade;

    @Autowired
    public ExpenseRequestAdminPresenter(ExpenseRequestFacade expenseRequestFacade, SourceFacade sourceFacade) {
        this.expenseRequestFacade = expenseRequestFacade;
        this.sourceFacade = sourceFacade;
    }

    @Override
    public void attachView(IExpenseRequestAdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void attachView(IExpenseRequestAddDialog addView) {
        this.addView = addView;
    }

    @Override
    public void attachView(IExpenseRequestEditDialog editView) {
        this.editView = editView;
    }

    @Override
    public void onClickSaveButton(ExpenseRequestModel expenseRequestModel) {
        if (expenseRequestModel.getId() != null) {
            if (!editView.hasChangesInForm()) {
                editView.showNoChangeErrorMessage();
                return;
            }
            if (editView.hasValidationErrors()) {
                editView.showValidationErrorMessages();
                return;
            }
            editView.hideErrorMessages();
            try {
                expenseRequestFacade.update(expenseRequestModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            editView.returnExpenseRequestAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();
            try {
                expenseRequestFacade.create(expenseRequestModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            addView.returnExpenseRequestAdminView();
        }
    }

    @Override
    public void attachView(IDeleteExpenseRequestConfirmDialog deleteView) {
        this.deleteView = deleteView;
    }

    @Override
    public void onClickOkButton(List<ExpenseRequestModel> expenseRequestModelList) {
        expenseRequestModelList.forEach(payer -> expenseRequestFacade.delete(payer.getId()));
        deleteView.returnExpenseRequestAdminView();
    }

    @Override
    public List<String> getSourceList() {
        return sourceFacade.listNames();
    }

    @Override
    public void loadExpenseRequestList() {
        adminView.bindingGridData(expenseRequestFacade.list());
    }

    @Override
    public void onClickSearchButton() {
        adminView.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        adminView.launchExpenseRequestAddDialog();
    }

    @Override
    public void onClickEditButton() {
        adminView.launchExpenseRequestEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        adminView.launchDeleteExpenseRequestConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<ExpenseRequestModel> selections = adminView.allGridSelections();
        if (selections.isEmpty()) adminView.toStateOfOnlyCanAdd();
        if (selections.size() == 1) adminView.toStateOfCanAll();
        if (selections.size() > 1) adminView.toStateOfCanAddAndDelete();
    }
}
