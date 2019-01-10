package org.pes.onecemulator.view.payeradmin.root.presenter;

import org.pes.onecemulator.model.internal.PayerModel;
import org.pes.onecemulator.view.payeradmin.dialog.add.IPayerAddDialog;
import org.pes.onecemulator.view.payeradmin.dialog.delete.IDeletePayerConfirmDialog;
import org.pes.onecemulator.view.payeradmin.dialog.edit.IPayerEditDialog;
import org.pes.onecemulator.view.payeradmin.root.view.IPayerAdminView;

import java.util.List;

public interface IPayerAdminPresenter {

    void attachView(IPayerAdminView view);

    void attachView(IPayerAddDialog view);

    void attachView(IPayerEditDialog view);

    void onClickSaveButton(PayerModel payerModel);

    void attachView(IDeletePayerConfirmDialog view);

    void onClickOkButton(List<PayerModel> payerModelList);

    List<String> getSourceList();

    void loadPayerList();

    void onClickSearchButton();

    void onClickAddButton();

    void onClickEditButton();

    void onClickDeleteButton();

    void onSelectGrid();
}
