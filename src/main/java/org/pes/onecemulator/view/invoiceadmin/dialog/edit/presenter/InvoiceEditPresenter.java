package org.pes.onecemulator.view.invoiceadmin.dialog.edit.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.pes.onecemulator.service.PayerService;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class InvoiceEditPresenter implements IInvoiceEditPresenter {

    private IInvoiceEditDialog view;

    private final InvoiceService invoiceService;

    private final SourceService sourceService;

    private final PayerService payerService;

    @Autowired
    public InvoiceEditPresenter(InvoiceService invoiceService, SourceService sourceService, PayerService payerService) {
        this.invoiceService = invoiceService;
        this.sourceService = sourceService;
        this.payerService = payerService;
    }

    @Override
    public void attachView(IInvoiceEditDialog view) {
        this.view = view;
    }

    @Override
    public List<String> getSources() {
        return sourceService.list().stream().map(SourceModel::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getPayers() {
        return payerService.list().stream().map(PayerModel::getCode).collect(Collectors.toList());
    }

    @Override
    public void onClickSaveButton(InvoiceModel invoiceModel) {
        if (!view.hasChangesInForm()) {
            view.showNoChangeErrorMessage();
            return;
        }
        if (view.hasValidationErrors()) {
            view.showValidationErrorMessages();
            return;
        }
        view.hideErrorMessages();

        InvoiceModel model = invoiceService.update(invoiceModel);
        if (model != null && model.getError() != null && !model.getError().isEmpty()) {
            Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
        }
        view.returnSourceAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnSourceAdminView();
    }
}
