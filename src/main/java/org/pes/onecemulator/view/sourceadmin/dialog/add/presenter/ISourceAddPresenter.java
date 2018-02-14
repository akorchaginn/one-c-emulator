package org.pes.onecemulator.view.sourceadmin.dialog.add.presenter;

import org.pes.onecemulator.model.SourceModel;

public interface ISourceAddPresenter {

    void attachView(ISourceAddDialog view);

    void onClickSaveButton(SourceModel sourceModel);

    void onClickCancelButton();
}
