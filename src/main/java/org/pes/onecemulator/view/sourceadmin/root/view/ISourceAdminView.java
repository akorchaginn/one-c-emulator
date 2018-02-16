package org.pes.onecemulator.view.sourceadmin.root.view;

import org.pes.onecemulator.model.SourceModel;

import java.util.List;

public interface ISourceAdminView {

    void bindingGridData(List<SourceModel> sourceModelList);

    void doFilterBySearchText();

    void toStateOfOnlyCanAdd();

    void toStateOfCanAll();

    void toStateOfCanAddAndDelete();

    void launchSourceAddDialog();

    void launchSourceEditDialog();

    void launchDeleteSourceConfirmDialog();

    List<SourceModel> allGridSelections();

    SourceModel gridSelection();
}
