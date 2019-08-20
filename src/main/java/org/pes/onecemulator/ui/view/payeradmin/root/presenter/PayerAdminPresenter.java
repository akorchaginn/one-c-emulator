package org.pes.onecemulator.ui.view.payeradmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.facade.PayerFacade;
import org.pes.onecemulator.facade.SourceFacade;
import org.pes.onecemulator.model.internal.PayerModel;
import org.pes.onecemulator.ui.view.fundamentals.notification.ErrorNotification;
import org.pes.onecemulator.ui.view.payeradmin.dialog.add.IPayerAddDialog;
import org.pes.onecemulator.ui.view.payeradmin.dialog.delete.IDeletePayerConfirmDialog;
import org.pes.onecemulator.ui.view.payeradmin.dialog.edit.IPayerEditDialog;
import org.pes.onecemulator.ui.view.payeradmin.root.view.IPayerAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class PayerAdminPresenter implements IPayerAdminPresenter {

    private IPayerAdminView adminView;

    private IPayerAddDialog addView;

    private IPayerEditDialog editView;

    private IDeletePayerConfirmDialog deleteView;

    private final PayerFacade payerFacade;

    private final SourceFacade sourceFacade;

    @Autowired
    public PayerAdminPresenter(PayerFacade payerFacade, SourceFacade sourceFacade) {
        this.payerFacade = payerFacade;
        this.sourceFacade = sourceFacade;
    }

    @Override
    public void attachView(IPayerAdminView adminView) {
        this.adminView = adminView;
    }

    @Override
    public void attachView(IPayerAddDialog addView) {
        this.addView = addView;
    }

    @Override
    public void attachView(IPayerEditDialog editView) {
        this.editView = editView;
    }

    @Override
    public void onClickSaveButton(PayerModel payerModel) {
        if (payerModel.getId() != null) {
            if (!editView.hasChangesInForm()) {
                editView.showNoChangeErrorMessage();
                return;
            }
            if (editView.hasValidationErrors()) {
                editView.showValidationErrorMessages();
                return;
            }
            editView.hideErrorMessages();
            try {
                payerFacade.update(payerModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            editView.returnPayerAdminView();
        } else {
            if (addView.hasValidationErrors()) {
                addView.showValidationErrorMessages();
                return;
            }
            addView.hideErrorMessages();
            try {
                payerFacade.create(payerModel);
            } catch (Exception e) {
                ErrorNotification.show(e);
            }
            addView.returnPayerAdminView();
        }
    }

    @Override
    public void attachView(IDeletePayerConfirmDialog deleteView) {
        this.deleteView = deleteView;
    }

    @Override
    public void onClickOkButton(List<PayerModel> payerModelList) {
        payerModelList.forEach(payer -> payerFacade.delete(payer.getId()));
        deleteView.returnPayerAdminView();
    }

    @Override
    public List<String> getSourceList() {
        return sourceFacade.listNames();
    }

    @Override
    public void loadPayerList() {
        adminView.bindingGridData(payerFacade.list());
    }

    @Override
    public void onClickSearchButton() {
        adminView.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        adminView.launchPayerAddDialog();
    }

    @Override
    public void onClickEditButton() {
        adminView.launchPayerEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        adminView.launchDeleteConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<PayerModel> selections = adminView.allGridSelections();
        if (selections.isEmpty()) adminView.toStateOfOnlyCanAdd();
        if (selections.size() == 1) adminView.toStateOfCanAll();
        if (selections.size() > 1) adminView.toStateOfCanAddAndDelete();
    }
}
