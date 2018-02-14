package org.pes.onecemulator.view.accountingentryadmin.dialog.edit.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.service.AccountingEntryService;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class AccountingEntryEditPresenter implements IAccountingEntryEditPresenter {

    private IAccountingEntryEditDialog view;

    private final AccountingEntryService accountingEntryService;

    private final ExpenseRequestService expenseRequestService;

    @Autowired
    public AccountingEntryEditPresenter(AccountingEntryService accountingEntryService, ExpenseRequestService expenseRequestService) {
        this.accountingEntryService = accountingEntryService;
        this.expenseRequestService = expenseRequestService;
    }

    @Override
    public void attachView(IAccountingEntryEditDialog view) {
        this.view = view;
    }

    @Override
    public List<String> getExpenseRequestList() {
        return expenseRequestService.list().stream().map(ExpenseRequestModel::getNumber).collect(Collectors.toList());
    }

    @Override
    public void onClickSaveButton(AccountingEntryModel accountingEntryModel) {
        if (!view.hasChangesInForm()) {
            view.showNoChangeErrorMessage();
            return;
        }
        if (view.hasValidationErrors()) {
            view.showValidationErrorMessages();
            return;
        }
        view.hideErrorMessages();

        AccountingEntryModel model = accountingEntryService.update(accountingEntryModel);
        if (model != null && model.getError() != null && !model.getError().isEmpty()) {
            Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
        }
        view.returnAccountingEntryAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnAccountingEntryAdminView();
    }
}
