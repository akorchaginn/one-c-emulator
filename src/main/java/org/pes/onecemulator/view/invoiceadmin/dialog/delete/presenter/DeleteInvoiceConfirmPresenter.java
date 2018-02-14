package org.pes.onecemulator.view.invoiceadmin.dialog.delete.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class DeleteInvoiceConfirmPresenter implements IDeleteInvoiceConfirmPresenter {

    private IDeleteInvoiceConfirmDialog view;

    private final InvoiceService invoiceService;

    @Autowired
    public DeleteInvoiceConfirmPresenter(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Override
    public void attachView(IDeleteInvoiceConfirmDialog view) {
        this.view = view;
    }

    @Override
    public void onClickOkButton(List<InvoiceModel> invoiceModelList) {
        invoiceModelList.forEach(sourceModel -> invoiceService.delete(sourceModel.getId()));
        view.returnSourceAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnSourceAdminView();
    }
}
