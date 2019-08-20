package org.pes.onecemulator.ui.view.sourceadmin.root.presenter;

import org.pes.onecemulator.model.internal.SourceModel;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.add.ISourceAddDialog;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.delete.IDeleteSourceConfirmDialog;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.edit.ISourceEditDialog;
import org.pes.onecemulator.ui.view.sourceadmin.root.view.ISourceAdminView;

import java.io.Serializable;
import java.util.List;

public interface ISourceAdminPresenter extends Serializable {

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
