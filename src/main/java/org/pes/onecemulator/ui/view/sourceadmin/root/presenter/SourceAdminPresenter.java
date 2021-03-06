package org.pes.onecemulator.ui.view.sourceadmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.facade.SourceFacade;
import org.pes.onecemulator.model.internal.SourceModel;
import org.pes.onecemulator.ui.view.fundamentals.notification.ErrorNotification;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.add.ISourceAddDialog;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.delete.IDeleteSourceConfirmDialog;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.edit.ISourceEditDialog;
import org.pes.onecemulator.ui.view.sourceadmin.root.view.ISourceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class SourceAdminPresenter implements ISourceAdminPresenter {

    private ISourceAdminView adminView;

    private ISourceAddDialog addView;

    private ISourceEditDialog editView;

    private IDeleteSourceConfirmDialog deleteView;

    private final SourceFacade sourceFacade;

    @Autowired
    public SourceAdminPresenter(SourceFacade sourceFacade) {
        this.sourceFacade = sourceFacade;
    }

    @Override
    public void attachView(ISourceAdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void attachView(ISourceAddDialog addView) {
        this.addView = addView;
    }

    @Override
    public void attachView(ISourceEditDialog editView) {
        this.editView = editView;
    }

    @Override
    public void onClickSaveButton(SourceModel sourceModel) {
        if (sourceModel.getId() != null) {
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
                sourceFacade.update(sourceModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            editView.returnSourceAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();
            try {
                sourceFacade.create(sourceModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            addView.returnSourceAdminView();
        }
    }

    @Override
    public void attachView(IDeleteSourceConfirmDialog deleteView) {
        this.deleteView = deleteView;
    }

    @Override
    public void onClickOkButton(List<SourceModel> sourceModelList) {
        sourceModelList.forEach(sourceModel -> sourceFacade.delete(sourceModel.getId()));
        deleteView.returnSourceAdminView();
    }

    @Override
    public void loadSourceList() {
        adminView.bindingGridData(sourceFacade.list());
    }

    @Override
    public void onClickSearchButton() {
        adminView.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        adminView.launchSourceAddDialog();
    }

    @Override
    public void onClickEditButton() {
        adminView.launchSourceEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        adminView.launchDeleteSourceConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<SourceModel> selections = adminView.allGridSelections();
        if (selections.isEmpty()) adminView.toStateOfOnlyCanAdd();
        if (selections.size() == 1) adminView.toStateOfCanAll();
        if (selections.size() > 1) adminView.toStateOfCanAddAndDelete();
    }
}
