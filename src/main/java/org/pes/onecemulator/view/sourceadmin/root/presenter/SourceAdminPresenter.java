package org.pes.onecemulator.view.sourceadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class SourceAdminPresenter implements ISourceAdminPresenter {

    private ISourceAdminView view;

    private final SourceService sourceService;

    @Autowired
    public SourceAdminPresenter(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(ISourceAdminView view) {
        this.view = view;
    }

    @Override
    public void loadSourceList() {
        view.bindingGridData(sourceService.list());
    }

    @Override
    public void onClickSearchButton() {
        view.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        view.launchSourceAddDialog();
    }

    @Override
    public void onClickEditButton() {
        view.launchSourceEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        view.launchDeleteSourceConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<SourceModel> selections = view.allGridSelections();
        if (selections.isEmpty()) view.toStateOfOnlyCanAdd();
        if (selections.size() == 1) view.toStateOfCanAll();
        if (selections.size() > 1) view.toStateOfCanAddAndDelete();
    }
}
