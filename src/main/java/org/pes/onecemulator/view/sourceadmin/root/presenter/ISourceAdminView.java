package org.pes.onecemulator.view.sourceadmin.root.presenter;

import org.pes.onecemulator.model.SourceModel;

import java.util.List;

public interface ISourceAdminView {

    void bindingGridData(List<SourceModel> sourceModelList);

    void doFilterBySearchText();

    void toStateOfOnlyCanAdd();

    void toStateOfCanAll();

    void toStateOfCanAddAndDelete();

    void launchPayerAddDialog();

    void launchPayerEditDialog();

    void launchDeleteConfirmDialog();

    List<SourceModel> allGridSelections();

    SourceModel gridSelection();
}
