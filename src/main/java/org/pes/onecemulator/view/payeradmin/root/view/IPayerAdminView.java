package org.pes.onecemulator.view.payeradmin.root.view;

import org.pes.onecemulator.model.PayerModel;

import java.util.List;

public interface IPayerAdminView {

    void bindingGridData(List<PayerModel> payerModelList);

    void doFilterBySearchText();

    void toStateOfOnlyCanAdd();

    void toStateOfCanAll();

    void toStateOfCanAddAndDelete();

    void launchPayerAddDialog();

    void launchPayerEditDialog();

    void launchDeleteConfirmDialog();

    List<PayerModel> allGridSelections();

    PayerModel gridSelection();
}
