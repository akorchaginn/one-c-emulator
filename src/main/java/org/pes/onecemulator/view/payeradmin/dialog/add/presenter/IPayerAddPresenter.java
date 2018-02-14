package org.pes.onecemulator.view.payeradmin.dialog.add.presenter;

import org.pes.onecemulator.model.PayerModel;

import java.util.List;

public interface IPayerAddPresenter {

    void attachView(IPayerAddDialog view);

    List<String> getSourceList();

    void onClickSaveButton(PayerModel payerModel);

    void onClickCancelButton();
}
