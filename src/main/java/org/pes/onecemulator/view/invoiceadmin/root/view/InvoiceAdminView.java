package org.pes.onecemulator.view.invoiceadmin.root.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.view.fundamentals.layout.BaseViewLayout;
import org.pes.onecemulator.view.invoiceadmin.dialog.add.InvoiceAddDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.delete.DeleteInvoiceConfirmDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.edit.InvoiceEditDialog;
import org.pes.onecemulator.view.invoiceadmin.root.presenter.IInvoiceAdminPresenter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = InvoiceAdminView.VIEW_NAME)
public class InvoiceAdminView extends BaseViewLayout implements View, IInvoiceAdminView {

    public static final String VIEW_NAME = "InvoiceAdminView";

    public static final String CAPTION = "Счета";

    public static final String TITLE = "1C-emulator: " + CAPTION;

    private final ViewHeader viewHeader = new ViewHeader(CAPTION);

    private final InvoiceAdminViewBody viewBody = new InvoiceAdminViewBody();

    private final IInvoiceAdminPresenter presenter;

    @Autowired
    public InvoiceAdminView(IInvoiceAdminPresenter presenter) {
        // 1st construction
        super();
        this.presenter = presenter;
        setCaption(CAPTION);
        UI.getCurrent().getPage().setTitle(TITLE);
        addHeaderAndBody(viewHeader, viewBody);
    }

    @PostConstruct
    void init() {
        // 2nd construction
        presenter.attachView(this);
        presenter.loadSourceList();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // 3rd construction
        viewBody.controlArea.addClickEventListenerToSearchButton(e -> presenter.onClickSearchButton());
        viewBody.controlArea.addClickEventListenerToAddButton(e -> presenter.onClickAddButton());
        viewBody.controlArea.addClickEventListenerToEditButton(e -> presenter.onClickEditButton());
        viewBody.controlArea.addClickEventListenerToDeleteButton(e -> presenter.onClickDeleteButton());
        viewBody.invoiceGrid.addSelectionListener(e -> presenter.onSelectGrid());
    }

    @Override
    public void bindingGridData(List<InvoiceModel> invoiceModelList) {
        viewBody.invoiceGrid.binding(invoiceModelList);
    }

    @Override
    public void doFilterBySearchText() {
        viewBody.invoiceGrid.filterBy(viewBody.controlArea.searchText());
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
    public List<InvoiceModel> allGridSelections() {
        return viewBody.invoiceGrid.allSelections();
    }

    @Override
    public InvoiceModel gridSelection() {
        return viewBody.invoiceGrid.selection();
    }

    @Override
    public void launchPayerAddDialog() {
        getUI().getNavigator().navigateTo(InvoiceAddDialog.VIEW_NAME);
    }

    @Override
    public void launchPayerEditDialog() {
        getUI().getNavigator().navigateTo(InvoiceEditDialog.VIEW_NAME);
    }

    @Override
    public void launchDeleteConfirmDialog() {
        getUI().getNavigator().navigateTo(DeleteInvoiceConfirmDialog.VIEW_NAME);
    }
}
