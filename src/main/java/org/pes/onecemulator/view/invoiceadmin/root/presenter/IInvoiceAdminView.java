package org.pes.onecemulator.view.invoiceadmin.root.presenter;

import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public interface IInvoiceAdminView {

    void bindingGridData(List<InvoiceModel> invoiceModelList);

    void doFilterBySearchText();

    void toStateOfOnlyCanAdd();

    void toStateOfCanAll();

    void toStateOfCanAddAndDelete();

    void launchPayerAddDialog();

    void launchPayerEditDialog();

    void launchDeleteConfirmDialog();

    List<InvoiceModel> allGridSelections();

    InvoiceModel gridSelection();
}
