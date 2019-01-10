package org.pes.onecemulator.view.invoiceadmin.root.view;

import org.pes.onecemulator.model.internal.InvoiceModel;

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
