package org.pes.onecemulator.ui.view.payeradmin.root.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.model.internal.PayerModel;
import org.pes.onecemulator.ui.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.ui.view.fundamentals.layout.BaseViewLayout;
import org.pes.onecemulator.ui.view.payeradmin.dialog.add.PayerAddDialog;
import org.pes.onecemulator.ui.view.payeradmin.dialog.delete.DeletePayerConfirmDialog;
import org.pes.onecemulator.ui.view.payeradmin.dialog.edit.PayerEditDialog;
import org.pes.onecemulator.ui.view.payeradmin.root.presenter.IPayerAdminPresenter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = PayerAdminView.VIEW_NAME)
public class PayerAdminView extends BaseViewLayout implements View, IPayerAdminView {

    public static final String VIEW_NAME = "PayerAdminView";

    public static final String CAPTION = "Плательщики";

    private static final String TITLE = "1C-emulator: " + CAPTION;

    private final PayerAdminViewBody viewBody = new PayerAdminViewBody();

    private final IPayerAdminPresenter presenter;

    @Autowired
    public PayerAdminView(IPayerAdminPresenter presenter) {
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
        presenter.loadPayerList();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // 3rd construction
        viewBody.controlArea.addClickEventListenerToSearchButton(e -> presenter.onClickSearchButton());
        viewBody.controlArea.addClickEventListenerToAddButton(e -> presenter.onClickAddButton());
        viewBody.controlArea.addClickEventListenerToEditButton(e -> presenter.onClickEditButton());
        viewBody.controlArea.addClickEventListenerToDeleteButton(e -> presenter.onClickDeleteButton());
        viewBody.payerGrid.addSelectionListener(e -> presenter.onSelectGrid());
    }

    @Override
    public void bindingGridData(List<PayerModel> payerModelList) {
        viewBody.payerGrid.binding(payerModelList);
    }

    @Override
    public void doFilterBySearchText() {
        viewBody.payerGrid.filterBy(viewBody.controlArea.searchText());
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
    public List<PayerModel> allGridSelections() {
        return viewBody.payerGrid.allSelections();
    }

    @Override
    public PayerModel gridSelection() {
        return viewBody.payerGrid.selection();
    }

    @Override
    public void launchPayerAddDialog() {
        getUI().getNavigator().navigateTo(PayerAddDialog.VIEW_NAME);
    }

    @Override
    public void launchPayerEditDialog() {
        getUI().getNavigator().navigateTo(PayerEditDialog.VIEW_NAME);
    }

    @Override
    public void launchDeleteConfirmDialog() {
        getUI().getNavigator().navigateTo(DeletePayerConfirmDialog.VIEW_NAME);
    }
}
