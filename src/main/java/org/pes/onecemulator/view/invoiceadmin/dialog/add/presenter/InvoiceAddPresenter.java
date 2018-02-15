package org.pes.onecemulator.view.invoiceadmin.dialog.add.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.InvoiceModel;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.InvoiceService;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class InvoiceAddPresenter implements IInvoiceAddPresenter {

    private IInvoiceAddDialog view;

    private final InvoiceService invoiceService;

    private final SourceService sourceService;

    @Autowired
    public InvoiceAddPresenter(InvoiceService invoiceService, SourceService sourceService) {
        this.invoiceService = invoiceService;
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(IInvoiceAddDialog view) {
        this.view = view;
    }

    @Override
    public List<String> getSourceList() {
        return sourceService.list().stream().map(SourceModel::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getPayerListBySource(String source) {
        return sourceService.getPayerList(source).stream().map(PayerModel::getCode).collect(Collectors.toList());
    }

    @Override
    public void onClickSaveButton(InvoiceModel invoiceModel) {
        if (view.hasValidationErrors()) {
            view.showValidationErrorMessages();
            return;
        }
        view.hideErrorMessages();

        InvoiceModel model = invoiceService.create(invoiceModel);
        if (model != null && model.getError() != null && !model.getError().isEmpty()) {
            Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
        }
        view.returnInvoiceAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnInvoiceAdminView();
    }
}
