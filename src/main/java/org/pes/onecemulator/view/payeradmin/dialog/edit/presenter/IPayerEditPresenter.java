package org.pes.onecemulator.view.payeradmin.dialog.edit.presenter;

import org.pes.onecemulator.model.PayerModel;

import java.util.List;

public interface IPayerEditPresenter {

    void attachView(IPayerEditDialog view);

    List<String> getSourceList();

    void onClickSaveButton(PayerModel payerModel);

    void onClickCancelButton();
}
