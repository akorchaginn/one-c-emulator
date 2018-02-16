package org.pes.onecemulator.view.accountingentryadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.pes.onecemulator.view.accountingentryadmin.dialog.add.IAccountingEntryAddDialog;
import org.pes.onecemulator.view.accountingentryadmin.dialog.delete.IDeleteAccountingEntryConfirmDialog;
import org.pes.onecemulator.view.accountingentryadmin.dialog.edit.IAccountingEntryEditDialog;
import org.pes.onecemulator.view.accountingentryadmin.root.view.IAccountingEntryAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class AccountingEntryAdminPresenter implements IAccountingEntryAdminPresenter {

    private IAccountingEntryAdminView adminView;

    private IAccountingEntryAddDialog addView;

    private IAccountingEntryEditDialog editView;

    private IDeleteAccountingEntryConfirmDialog deleteView;

    private final AccountingEntryService accountingEntryService;

    private final ExpenseRequestService expenseRequestService;

    @Autowired
    public AccountingEntryAdminPresenter(AccountingEntryService accountingEntryService, ExpenseRequestService expenseRequestService) {
        this.accountingEntryService = accountingEntryService;
        this.expenseRequestService = expenseRequestService;
    }

    @Override
    public void attachView(IAccountingEntryAdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void attachView(IAccountingEntryAddDialog addView) {
        this.addView = addView;
    }

    @Override
    public void attachView(IAccountingEntryEditDialog editView) {
        this.editView = editView;
    }

    @Override
    public void onClickSaveButton(AccountingEntryModel accountingEntryModel) {
        if (accountingEntryModel.getId() != null) {
            if (!editView.hasChangesInForm()) {
                editView.showNoChangeErrorMessage();
                return;
            }
            if (editView.hasValidationErrors()) {
                editView.showValidationErrorMessages();
                return;
            }
            editView.hideErrorMessages();

            AccountingEntryModel model = accountingEntryService.update(accountingEntryModel);
            if (model != null && model.getError() != null && !model.getError().isEmpty()) {
                Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
            }
            editView.returnAccountingEntryAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();

            AccountingEntryModel model = accountingEntryService.create(accountingEntryModel);
            if (model != null && model.getError() != null && !model.getError().isEmpty()) {
                Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
            }
            addView.returnAccountingEntryAdminView();
        }
    }

    @Override
    public void attachView(IDeleteAccountingEntryConfirmDialog deleteView) {
        this.deleteView = deleteView;
    }

    @Override
    public void onClickOkButton(List<AccountingEntryModel> accountingEntryModelList) {
        accountingEntryModelList.forEach(ae -> accountingEntryService.delete(ae.getId()));
        deleteView.returnAccountingEntryAdminView();
    }

    @Override
    public List<String> getExpenseRequestList() {
        return expenseRequestService.list().stream().map(ExpenseRequestModel::getNumber).collect(Collectors.toList());
    }

    @Override
    public void loadAccountingEntryList() {
        adminView.bindingGridData(accountingEntryService.list());
    }

    @Override
    public void onClickSearchButton() {
        adminView.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        adminView.launchAccountingEntryAddDialog();
    }

    @Override
    public void onClickEditButton() {
        adminView.launchAccountingEntryEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        adminView.launchDeleteAccountingEntryConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<AccountingEntryModel> selections = adminView.allGridSelections();
        if (selections.isEmpty()) adminView.toStateOfOnlyCanAdd();
        if (selections.size() == 1) adminView.toStateOfCanAll();
        if (selections.size() > 1) adminView.toStateOfCanAddAndDelete();
    }
}
