package org.pes.onecemulator.view.payeradmin.dialog.delete.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.service.PayerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class DeletePayerConfirmPresenter implements IDeletePayerConfirmPresenter {

    private IDeletePayerConfirmDialog view;

    private final PayerService payerService;

    @Autowired
    public DeletePayerConfirmPresenter(PayerService payerService) {
        this.payerService = payerService;
    }

    @Override
    public void attachView(IDeletePayerConfirmDialog view) {
        this.view = view;
    }

    @Override
    public void onClickOkButton(List<PayerModel> payerModelList) {
        payerModelList.forEach(payer -> payerService.delete(payer.getId()));
        view.returnPayerAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnPayerAdminView();
    }
}
