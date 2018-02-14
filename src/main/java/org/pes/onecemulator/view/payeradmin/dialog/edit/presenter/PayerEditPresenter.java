package org.pes.onecemulator.view.payeradmin.dialog.edit.presenter;

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
public class PayerEditPresenter implements IPayerEditPresenter {

    private IPayerEditDialog view;

    private final PayerService payerService;

    private final SourceService sourceService;

    @Autowired
    public PayerEditPresenter(PayerService payerService, SourceService sourceService) {
        this.payerService = payerService;
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(IPayerEditDialog view) {
        this.view = view;
    }

    @Override
    public List<String> getSourceList() {
        return sourceService.list().stream().map(SourceModel::getName).collect(Collectors.toList());
    }

    @Override
    public void onClickSaveButton(PayerModel payerModel) {
        if (!view.hasChangesInForm()) {
            view.showNoChangeErrorMessage();
            return;
        }
        if (view.hasValidationErrors()) {
            view.showValidationErrorMessages();
            return;
        }
        view.hideErrorMessages();

        PayerModel model = payerService.update(payerModel);
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
