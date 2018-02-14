package org.pes.onecemulator.view.invoiceadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class InvoiceAdminPresenter implements IInvoiceAdminPresenter {

    private IInvoiceAdminView view;

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceAdminPresenter(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public void attachView(IInvoiceAdminView view) {
        this.view = view;
    }

    @Override
    public void loadSources() {
        view.bindingGridData(invoiceService.list());
    }

    @Override
    public void onClickSearchButton() {
        view.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        view.launchPayerAddDialog();
    }

    @Override
    public void onClickEditButton() {
        view.launchPayerEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        view.launchDeleteConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<InvoiceModel> selections = view.allGridSelections();
        if (selections.isEmpty()) view.toStateOfOnlyCanAdd();
        if (selections.size() == 1) view.toStateOfCanAll();
        if (selections.size() > 1) view.toStateOfCanAddAndDelete();
    }
}
