package org.pes.onecemulator.view.accountingentryadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.AccountingEntryModel;
import org.pes.onecemulator.service.AccountingEntryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class AccountingEntryAdminPresenter implements IAccountingEntryAdminPresenter {

    private IAccountingEntryAdminView view;

    private final AccountingEntryService accountingEntryService;

    @Autowired
    public AccountingEntryAdminPresenter(AccountingEntryService accountingEntryService) {
        this.accountingEntryService = accountingEntryService;
    }

    @Override
    public void attachView(IAccountingEntryAdminView view) {
        this.view = view;
    }

    @Override
    public void loadAccountingEntryList() {
        view.bindingGridData(accountingEntryService.list());
    }

    @Override
    public void onClickSearchButton() {
        view.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        view.launchAccountingEntryAddDialog();
    }

    @Override
    public void onClickEditButton() {
        view.launchAccountingEntryEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        view.launchDeleteAccountingEntryConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<AccountingEntryModel> selections = view.allGridSelections();
        if (selections.isEmpty()) view.toStateOfOnlyCanAdd();
        if (selections.size() == 1) view.toStateOfCanAll();
        if (selections.size() > 1) view.toStateOfCanAddAndDelete();
    }
}
