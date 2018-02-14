package org.pes.onecemulator.view.sourceadmin.dialog.add.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class SourceAddPresenter implements ISourceAddPresenter {

    private ISourceAddDialog view;

    private final SourceService sourceService;

    @Autowired
    public SourceAddPresenter(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(ISourceAddDialog view) {
        this.view = view;
    }

    @Override
    public void onClickSaveButton(SourceModel sourceModel) {
        if (view.hasValidationErrors()) {
            view.showValidationErrorMessages();
            return;
        }
        view.hideErrorMessages();

        SourceModel model = sourceService.create(sourceModel);
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
