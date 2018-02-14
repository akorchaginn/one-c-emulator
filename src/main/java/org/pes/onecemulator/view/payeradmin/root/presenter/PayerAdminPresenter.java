package org.pes.onecemulator.view.payeradmin.root.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.service.PayerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
@ViewScope
public class PayerAdminPresenter implements IPayerAdminPresenter {

    private IPayerAdminView view;

    private final PayerService payerService;

    @Autowired
    public PayerAdminPresenter(PayerService payerService) {
        this.payerService = payerService;
    }

    @Override
    public void attachView(IPayerAdminView view) {
        this.view = view;
    }

    @Override
    public void loadPayers() {
        view.bindingGridData(payerService.list());
    }

    @Override
    public void onClickSearchButton() {
        view.doFilterBySearchText();
    }

    @Override
    public void onClickAddButton() {
        view.launchPayerAddDialog();
    }

    @Override
    public void onClickEditButton() {
        view.launchPayerEditDialog();
    }

    @Override
    public void onClickDeleteButton() {
        view.launchDeleteConfirmDialog();
    }

    @Override
    public void onSelectGrid() {
        List<PayerModel> selections = view.allGridSelections();
        if (selections.isEmpty()) view.toStateOfOnlyCanAdd();
        if (selections.size() == 1) view.toStateOfCanAll();
        if (selections.size() > 1) view.toStateOfCanAddAndDelete();
    }
}
