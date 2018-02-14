package org.pes.onecemulator.view.payeradmin.dialog.add.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.PayerService;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class PayerAddPresenter implements IPayerAddPresenter {

    private IPayerAddDialog view;

    private final PayerService payerService;

    private final SourceService sourceService;

    @Autowired
    public PayerAddPresenter(PayerService payerService, SourceService sourceService) {
        this.payerService = payerService;
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(IPayerAddDialog view) {
        this.view = view;
    }

    @Override
    public List<String> getSourceList() {
        return sourceService.list().stream().map(SourceModel::getName).collect(Collectors.toList());
    }

    @Override
    public void onClickSaveButton(PayerModel payerModel) {
        if (view.hasValidationErrors()) {
            view.showValidationErrorMessages();
            return;
        }
        view.hideErrorMessages();

        PayerModel model = payerService.create(payerModel);
        if (model != null && model.getError() != null && !model.getError().isEmpty()) {
            Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
        }
        view.returnPayerAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnPayerAdminView();
    }
}
