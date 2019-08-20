package org.pes.onecemulator.ui.view.accountingentryadmin.root.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.model.internal.AccountingEntryModel;
import org.pes.onecemulator.ui.view.accountingentryadmin.dialog.add.AccountingEntryAddDialog;
import org.pes.onecemulator.ui.view.accountingentryadmin.dialog.delete.DeleteAccountingEntryConfirmDialog;
import org.pes.onecemulator.ui.view.accountingentryadmin.dialog.edit.AccountingEntryEditDialog;
import org.pes.onecemulator.ui.view.accountingentryadmin.root.presenter.IAccountingEntryAdminPresenter;
import org.pes.onecemulator.ui.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.ui.view.fundamentals.layout.BaseViewLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = AccountingEntryAdminView.VIEW_NAME)
public class AccountingEntryAdminView extends BaseViewLayout implements View, IAccountingEntryAdminView {

    public static final String VIEW_NAME = "AccountingEntryAdminView";

    public static final String CAPTION = "Проводки";

    private static final String TITLE = "1C-emulator: " + CAPTION;

    private final AccountingEntryAdminViewBody viewBody = new AccountingEntryAdminViewBody();

    private final IAccountingEntryAdminPresenter presenter;

    @Autowired
    public AccountingEntryAdminView(IAccountingEntryAdminPresenter presenter) {
        // 1st construction
        super();
        this.presenter = presenter;
        setCaption(CAPTION);
        UI.getCurrent().getPage().setTitle(TITLE);
        ViewHeader viewHeader = new ViewHeader(CAPTION);
        addHeaderAndBody(viewHeader, viewBody);
    }

    @PostConstruct
    void init() {
        // 2nd construction
        presenter.attachView(this);
        presenter.loadAccountingEntryList();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // 3rd construction
        viewBody.controlArea.addClickEventListenerToSearchButton(e -> presenter.onClickSearchButton());
        viewBody.controlArea.addClickEventListenerToAddButton(e -> presenter.onClickAddButton());
        viewBody.controlArea.addClickEventListenerToEditButton(e -> presenter.onClickEditButton());
        viewBody.controlArea.addClickEventListenerToDeleteButton(e -> presenter.onClickDeleteButton());
        viewBody.accountingEntryGrid.addSelectionListener(e -> presenter.onSelectGrid());
    }

    @Override
    public void bindingGridData(List<AccountingEntryModel> accountingEntryModelList) {
        viewBody.accountingEntryGrid.binding(accountingEntryModelList);
    }

    @Override
    public void doFilterBySearchText() {
        viewBody.accountingEntryGrid.filterBy(viewBody.controlArea.searchText());
    }

    @Override
    public void toStateOfOnlyCanAdd() {
        viewBody.controlArea.toStateOfOnlyCanAdd();
    }

    @Override
    public void toStateOfCanAll() {
        viewBody.controlArea.toStateOfCanAll();
    }

    @Override
    public void toStateOfCanAddAndDelete() {
        viewBody.controlArea.toStateOfCanAddAndDelete();
    }

    @Override
    public List<AccountingEntryModel> allGridSelections() {
        return viewBody.accountingEntryGrid.allSelections();
    }

    @Override
    public AccountingEntryModel gridSelection() {
        return viewBody.accountingEntryGrid.selection();
    }

    @Override
    public void launchAccountingEntryAddDialog() {
        getUI().getNavigator().navigateTo(AccountingEntryAddDialog.VIEW_NAME);
    }

    @Override
    public void launchAccountingEntryEditDialog() {
        getUI().getNavigator().navigateTo(AccountingEntryEditDialog.VIEW_NAME);
    }

    @Override
    public void launchDeleteAccountingEntryConfirmDialog() {
        getUI().getNavigator().navigateTo(DeleteAccountingEntryConfirmDialog.VIEW_NAME);
    }
}
