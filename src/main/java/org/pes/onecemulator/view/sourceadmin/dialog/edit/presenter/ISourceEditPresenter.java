package org.pes.onecemulator.view.sourceadmin.dialog.edit.presenter;

import org.pes.onecemulator.model.SourceModel;

public interface ISourceEditPresenter {

    void attachView(ISourceEditDialog view);

    void onClickSaveButton(SourceModel sourceModel);

    void onClickCancelButton();
}
