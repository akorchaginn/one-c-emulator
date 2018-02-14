package org.pes.onecemulator.view.accountingentryadmin.dialog.delete.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.service.AccountingEntryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class DeleteAccountingEntryConfirmPresenter implements IDeleteAccountingEntryConfirmPresenter {

    private IDeleteAccountingEntryConfirmDialog view;

    private final AccountingEntryService accountingEntryService;

    @Autowired
    public DeleteAccountingEntryConfirmPresenter(AccountingEntryService accountingEntryService) {
        this.accountingEntryService = accountingEntryService;
    }

    @Override
    public void attachView(IDeleteAccountingEntryConfirmDialog view) {
        this.view = view;
    }

    @Override
    public void onClickOkButton(List<AccountingEntryModel> accountingEntryModelList) {
        accountingEntryModelList.forEach(ae -> accountingEntryService.delete(ae.getId()));
        view.returnAccountingEntryAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnAccountingEntryAdminView();
    }
}
