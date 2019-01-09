package org.pes.onecemulator.view.sourceadmin.root.presenter;

import org.pes.onecemulator.model.internal.SourceModel;
import org.pes.onecemulator.view.sourceadmin.dialog.add.ISourceAddDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.delete.IDeleteSourceConfirmDialog;
import org.pes.onecemulator.view.sourceadmin.dialog.edit.ISourceEditDialog;
import org.pes.onecemulator.view.sourceadmin.root.view.ISourceAdminView;

import java.util.List;

public interface ISourceAdminPresenter {

    void attachView(ISourceAdminView view);

    void loadSourceList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();

    void attachView(ISourceAddDialog view);

    void attachView(ISourceEditDialog view);

    void onClickSaveButton(SourceModel sourceModel);

    void attachView(IDeleteSourceConfirmDialog view);

    void onClickOkButton(List<SourceModel> sourceModelList);
}
