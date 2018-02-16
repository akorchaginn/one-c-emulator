package org.pes.onecemulator.view.invoiceadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.pes.onecemulator.service.SourceService;
import org.pes.onecemulator.view.invoiceadmin.dialog.add.IInvoiceAddDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.delete.IDeleteInvoiceConfirmDialog;
import org.pes.onecemulator.view.invoiceadmin.dialog.edit.IInvoiceEditDialog;
import org.pes.onecemulator.view.invoiceadmin.root.view.IInvoiceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class InvoiceAdminPresenter implements IInvoiceAdminPresenter {

    private IInvoiceAdminView adminView;

    private IInvoiceAddDialog addView;

    private IInvoiceEditDialog editView;

    private IDeleteInvoiceConfirmDialog deleteView;

    private final InvoiceService invoiceService;

    private final SourceService sourceService;

    @Autowired
    public InvoiceAdminPresenter(InvoiceService invoiceService, SourceService sourceService) {
        this.invoiceService = invoiceService;
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(IInvoiceAdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void attachView(IInvoiceAddDialog addView) {
        this.addView = addView;
    }

    @Override
    public void attachView(IInvoiceEditDialog editView) {
        this.editView = editView;
    }

    @Override
    public void onClickSaveButton(InvoiceModel invoiceModel) {
        if (invoiceModel.getId() != null) {
            if (!editView.hasChangesInForm()) {
                editView.showNoChangeErrorMessage();
                return;
            }
            if (editView.hasValidationErrors()) {
                editView.showValidationErrorMessages();
                return;
            }
            editView.hideErrorMessages();

            InvoiceModel model = invoiceService.update(invoiceModel);
            if (model != null && model.getError() != null && !model.getError().isEmpty()) {
                Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
            }
            editView.returnInvoiceAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();

            InvoiceModel model = invoiceService.create(invoiceModel);
            if (model != null && model.getError() != null && !model.getError().isEmpty()) {
                Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
            }
            addView.returnInvoiceAdminView();
        }
    }

    @Override
    public void attachView(IDeleteInvoiceConfirmDialog deleteView) {
        this.deleteView = deleteView;
    }

    @Override
    public void onClickOkButton(List<InvoiceModel> invoiceModelList) {
        invoiceModelList.forEach(sourceModel -> invoiceService.delete(sourceModel.getId()));
        deleteView.returnInvoiceAdminView();
    }

    @Override
    public List<String> getSourceList() {
        return sourceService.list().stream().map(SourceModel::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getPayerListBySource(String source) {
        return sourceService.getPayerList(source).stream().map(PayerModel::getCode).collect(Collectors.toList());
    }

    @Override
    public void loadSourceList() {
        adminView.bindingGridData(invoiceService.list());
    }

    @Override
    public void onClickSearchButton() {
        adminView.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        adminView.launchPayerAddDialog();
    }

    @Override
    public void onClickEditButton() {
        adminView.launchPayerEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        adminView.launchDeleteConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<InvoiceModel> selections = adminView.allGridSelections();
        if (selections.isEmpty()) adminView.toStateOfOnlyCanAdd();
        if (selections.size() == 1) adminView.toStateOfCanAll();
        if (selections.size() > 1) adminView.toStateOfCanAddAndDelete();
    }
}
