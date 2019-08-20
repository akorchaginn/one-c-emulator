package org.pes.onecemulator.ui.view.invoiceadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.facade.InvoiceFacade;
import org.pes.onecemulator.facade.PayerFacade;
import org.pes.onecemulator.facade.SourceFacade;
import org.pes.onecemulator.model.internal.InvoiceModel;
import org.pes.onecemulator.ui.view.fundamentals.notification.ErrorNotification;
import org.pes.onecemulator.ui.view.invoiceadmin.dialog.add.IInvoiceAddDialog;
import org.pes.onecemulator.ui.view.invoiceadmin.dialog.delete.IDeleteInvoiceConfirmDialog;
import org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit.IInvoiceEditDialog;
import org.pes.onecemulator.ui.view.invoiceadmin.root.view.IInvoiceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class InvoiceAdminPresenter implements IInvoiceAdminPresenter {

    private IInvoiceAdminView adminView;

    private IInvoiceAddDialog addView;

    private IInvoiceEditDialog editView;

    private IDeleteInvoiceConfirmDialog deleteView;

    private final PayerFacade payerFacade;

    private final InvoiceFacade invoiceFacade;

    private final SourceFacade sourceFacade;

    @Autowired
    public InvoiceAdminPresenter(PayerFacade payerFacade, InvoiceFacade invoiceFacade, SourceFacade sourceFacade) {
        this.payerFacade = payerFacade;
        this.invoiceFacade = invoiceFacade;
        this.sourceFacade = sourceFacade;
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

            try {
                invoiceFacade.update(invoiceModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            editView.returnInvoiceAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();
            try {
                invoiceFacade.create(invoiceModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
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
        invoiceModelList.forEach(sourceModel -> invoiceFacade.delete(sourceModel.getId()));
        deleteView.returnInvoiceAdminView();
    }

    @Override
    public List<String> getSourceList() {
        return sourceFacade.listNames();
    }

    @Override
    public List<String> getPayerListBySource(String source) {
        return payerFacade.listCodeBySource(source);
    }

    @Override
    public void loadSourceList() {
        adminView.bindingGridData(invoiceFacade.list());
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
