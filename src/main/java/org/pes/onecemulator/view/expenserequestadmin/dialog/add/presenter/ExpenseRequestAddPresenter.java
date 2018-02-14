package org.pes.onecemulator.view.expenserequestadmin.dialog.add.presenter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@ViewScope
public class ExpenseRequestAddPresenter implements IExpenseRequestAddPresenter {

    private IExpenseRequestAddDialog view;

    private final ExpenseRequestService expenseRequestService;

    private final SourceService sourceService;

    @Autowired
    public ExpenseRequestAddPresenter(ExpenseRequestService expenseRequestService, SourceService sourceService) {
        this.expenseRequestService = expenseRequestService;
        this.sourceService = sourceService;
    }

    @Override
    public void attachView(IExpenseRequestAddDialog view) {
        this.view = view;
    }

    @Override
    public List<String> getSourceList() {
        return sourceService.list().stream().map(SourceModel::getName).collect(Collectors.toList());
    }

    @Override
    public void onClickSaveButton(ExpenseRequestModel expenseRequestModel) {
        if (view.hasValidationErrors()) {
            view.showValidationErrorMessages();
            return;
        }
        view.hideErrorMessages();

        ExpenseRequestModel model = expenseRequestService.create(expenseRequestModel);
        if (model != null && model.getError() != null && !model.getError().isEmpty()) {
            Notification.show(model.getError(), Notification.Type.ERROR_MESSAGE);
        }
        view.returnExpenseRequestAdminView();
    }

    @Override
    public void onClickCancelButton() {
        view.returnExpenseRequestAdminView();
    }
}
