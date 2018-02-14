package org.pes.onecemulator.view.sourceadmin.dialog.delete.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class DeleteSourceConfirmPresenter implements IDeleteSourceConfirmPresenter {

    private IDeleteSourceConfirmDialog view;

    private final SourceService sourceService;

    @Autowired
    public DeleteSourceConfirmPresenter(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(IDeleteSourceConfirmDialog view) {
        this.view = view;
    }

    @Override
    public void onClickOkButton(List<SourceModel> payerModelList) {
        payerModelList.forEach(sourceModel -> sourceService.delete(sourceModel.getId()));
        view.returnSourceAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnSourceAdminView();
    }
}
