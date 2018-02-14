package org.pes.onecemulator.view.sourceadmin.dialog.delete.presenter;

import org.pes.onecemulator.model.SourceModel;

import java.util.List;

public interface IDeleteSourceConfirmPresenter {

    void attachView(IDeleteSourceConfirmDialog view);

    void onClickOkButton(List<SourceModel> sourceModelList);

    void onClickCancelButton();
}
