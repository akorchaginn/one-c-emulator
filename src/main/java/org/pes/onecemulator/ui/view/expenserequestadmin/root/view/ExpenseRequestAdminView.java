package org.pes.onecemulator.ui.view.expenserequestadmin.root.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.add.ExpenseRequestAddDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.delete.DeleteExpenseRequestConfirmDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.dialog.edit.ExpenseRequestEditDialog;
import org.pes.onecemulator.ui.view.expenserequestadmin.root.presenter.IExpenseRequestAdminPresenter;
import org.pes.onecemulator.ui.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.ui.view.fundamentals.layout.BaseViewLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = ExpenseRequestAdminView.VIEW_NAME)
public class ExpenseRequestAdminView extends BaseViewLayout implements View, IExpenseRequestAdminView {

    public static final String VIEW_NAME = "ExpenseRequestAdminView";

    public static final String CAPTION = "Заявки на расход";

    private static final String TITLE = "1C-emulator: " + CAPTION;

    private final ExpenseRequestAdminViewBody viewBody = new ExpenseRequestAdminViewBody();

    private final IExpenseRequestAdminPresenter presenter;

    @Autowired
    public ExpenseRequestAdminView(IExpenseRequestAdminPresenter presenter) {
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
        presenter.loadExpenseRequestList();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // 3rd construction
        viewBody.controlArea.addClickEventListenerToSearchButton(e -> presenter.onClickSearchButton());
        viewBody.controlArea.addClickEventListenerToAddButton(e -> presenter.onClickAddButton());
        viewBody.controlArea.addClickEventListenerToEditButton(e -> presenter.onClickEditButton());
        viewBody.controlArea.addClickEventListenerToDeleteButton(e -> presenter.onClickDeleteButton());
        viewBody.expenseRequestGrid.addSelectionListener(e -> presenter.onSelectGrid());
    }

    @Override
    public void bindingGridData(List<ExpenseRequestModel> expenseRequestModelList) {
        viewBody.expenseRequestGrid.binding(expenseRequestModelList);
    }

    @Override
    public void doFilterBySearchText() {
        viewBody.expenseRequestGrid.filterBy(viewBody.controlArea.searchText());
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
    public List<ExpenseRequestModel> allGridSelections() {
        return viewBody.expenseRequestGrid.allSelections();
    }

    @Override
    public ExpenseRequestModel gridSelection() {
        return viewBody.expenseRequestGrid.selection();
    }

    @Override
    public void launchExpenseRequestAddDialog() {
        getUI().getNavigator().navigateTo(ExpenseRequestAddDialog.VIEW_NAME);
    }

    @Override
    public void launchExpenseRequestEditDialog() {
        getUI().getNavigator().navigateTo(ExpenseRequestEditDialog.VIEW_NAME);
    }

    @Override
    public void launchDeleteExpenseRequestConfirmDialog() {
        getUI().getNavigator().navigateTo(DeleteExpenseRequestConfirmDialog.VIEW_NAME);
    }
}
