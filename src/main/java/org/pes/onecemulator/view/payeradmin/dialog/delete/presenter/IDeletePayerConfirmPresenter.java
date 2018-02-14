package org.pes.onecemulator.view.payeradmin.dialog.delete.presenter;

import org.pes.onecemulator.model.PayerModel;

import java.util.List;

public interface IDeletePayerConfirmPresenter {

    void attachView(IDeletePayerConfirmDialog view);

    void onClickOkButton(List<PayerModel> payerModelList);

    void onClickCancelButton();
}
