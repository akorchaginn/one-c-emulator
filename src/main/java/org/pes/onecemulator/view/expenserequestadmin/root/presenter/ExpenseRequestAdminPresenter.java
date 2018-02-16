package org.pes.onecemulator.view.expenserequestadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.pes.onecemulator.service.SourceService;
import org.pes.onecemulator.view.expenserequestadmin.dialog.add.IExpenseRequestAddDialog;
import org.pes.onecemulator.view.expenserequestadmin.dialog.delete.IDeleteExpenseRequestConfirmDialog;
import org.pes.onecemulator.view.expenserequestadmin.dialog.edit.IExpenseRequestEditDialog;
import org.pes.onecemulator.view.expenserequestadmin.root.view.IExpenseRequestAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class ExpenseRequestAdminPresenter implements IExpenseRequestAdminPresenter {

    private IExpenseRequestAdminView adminView;

    private IExpenseRequestAddDialog addView;

    private IExpenseRequestEditDialog editView;

    private IDeleteExpenseRequestConfirmDialog deleteView;

    private final ExpenseRequestService expenseRequestService;

    private final SourceService sourceService;

    @Autowired
    public ExpenseRequestAdminPresenter(ExpenseRequestService expenseRequestService, SourceService sourceService) {
        this.expenseRequestService = expenseRequestService;
        this.sourceService = sourceService;
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

            ExpenseRequestModel model = expenseRequestService.update(expenseRequestModel);
            if (model != null && model.getError() != null && !model.getError().isEmpty()) {
                Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
            }
            editView.returnExpenseRequestAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();

            ExpenseRequestModel model = expenseRequestService.create(expenseRequestModel);
            if (model != null && model.getError() != null && !model.getError().isEmpty()) {
                Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
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
        expenseRequestModelList.forEach(payer -> expenseRequestService.delete(payer.getId()));
        deleteView.returnExpenseRequestAdminView();
    }

    @Override
    public List<String> getSourceList() {
        return sourceService.list().stream().map(SourceModel::getName).collect(Collectors.toList());
    }

    @Override
    public void loadExpenseRequestList() {
        adminView.bindingGridData(expenseRequestService.list());
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
