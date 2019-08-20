package org.pes.onecemulator.ui.view.accountingentryadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.facade.AccountingEntryFacade;
import org.pes.onecemulator.facade.ExpenseRequestFacade;
import org.pes.onecemulator.model.internal.AccountingEntryModel;
import org.pes.onecemulator.ui.view.accountingentryadmin.dialog.add.IAccountingEntryAddDialog;
import org.pes.onecemulator.ui.view.accountingentryadmin.dialog.delete.IDeleteAccountingEntryConfirmDialog;
import org.pes.onecemulator.ui.view.accountingentryadmin.dialog.edit.IAccountingEntryEditDialog;
import org.pes.onecemulator.ui.view.accountingentryadmin.root.view.IAccountingEntryAdminView;
import org.pes.onecemulator.ui.view.fundamentals.notification.ErrorNotification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class AccountingEntryAdminPresenter implements IAccountingEntryAdminPresenter {

    private IAccountingEntryAdminView adminView;

    private IAccountingEntryAddDialog addView;

    private IAccountingEntryEditDialog editView;

    private IDeleteAccountingEntryConfirmDialog deleteView;

    private final AccountingEntryFacade accountingEntryFacade;

    private final ExpenseRequestFacade expenseRequestFacade;

    @Autowired
    public AccountingEntryAdminPresenter(final AccountingEntryFacade accountingEntryFacade,
                                         final ExpenseRequestFacade expenseRequestFacade) {
        this.accountingEntryFacade = accountingEntryFacade;
        this.expenseRequestFacade = expenseRequestFacade;
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
            try {
                accountingEntryFacade.update(accountingEntryModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            editView.returnAccountingEntryAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();
            try {
                accountingEntryFacade.create(accountingEntryModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
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
        accountingEntryModelList.forEach(ae -> accountingEntryFacade.delete(ae.getId()));
        deleteView.returnAccountingEntryAdminView();
    }

    @Override
    public List<String> getExpenseRequestList() {
        return expenseRequestFacade.listNumbers();
    }

    @Override
    public void loadAccountingEntryList() {
        adminView.bindingGridData(accountingEntryFacade.list());
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
