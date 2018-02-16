package org.pes.onecemulator.view.expenserequestadmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;

public class ExpenseRequestEditForm extends FormLayout {

    private final ExpenseRequestIdReadOnlyField expenseRequestId;

    private final ExpenseRequestSourceEditField expenseRequestSource;

    private final ExpenseRequestCurrencyEditField expenseRequestCurrency;

    private final ExpenseRequestConfirmEditField expenseRequestConfirm;

    private final ExpenseRequestPaidEditField expenseRequestPaid;

    private final ExpenseRequestNumberEditField expenseRequestNumber;

    private final ExpenseRequestSumEditField expenseRequestSum;

    //Version originalVersion;

    ExpenseRequestEditForm(ExpenseRequestModel target, List<String> sourceList) {
        this.expenseRequestId = new ExpenseRequestIdReadOnlyField(target.getId());
        this.expenseRequestSource = new ExpenseRequestSourceEditField(target.getSource(), sourceList);
        this.expenseRequestCurrency = new ExpenseRequestCurrencyEditField(target.getCurrency());
        this.expenseRequestConfirm = new ExpenseRequestConfirmEditField(target.getConfirm());
        this.expenseRequestPaid = new ExpenseRequestPaidEditField(target.getPaid());
        this.expenseRequestNumber = new ExpenseRequestNumberEditField(target.getNumber());
        this.expenseRequestSum = new ExpenseRequestSumEditField(target.getSum());
        //this.originalVersion = targetSummary.audit().version();

        addComponents(
                expenseRequestId,
                expenseRequestSource,
                expenseRequestCurrency,
                expenseRequestConfirm,
                expenseRequestPaid,
                expenseRequestNumber,
                expenseRequestSum);
        setMargin(false);
    }

    void validate() {
        expenseRequestSource.binder.validate();
        expenseRequestCurrency.binder.validate();
        expenseRequestConfirm.binder.validate();
        expenseRequestPaid.binder.validate();
        expenseRequestNumber.binder.validate();
        expenseRequestSum.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return expenseRequestSource.binder.isValid()
                && expenseRequestCurrency.binder.isValid()
                && expenseRequestConfirm.binder.isValid()
                && expenseRequestPaid.binder.isValid()
                && expenseRequestNumber.binder.isValid()
                && expenseRequestSum.binder.isValid();
    }

    boolean hasChanges() {
        // note: binder.setBean() and binder.hasChange() are not work I expected
        return expenseRequestSource.hasChanges()
                || expenseRequestCurrency.hasChanges()
                || expenseRequestConfirm.hasChanges()
                || expenseRequestPaid.hasChanges()
                || expenseRequestNumber.hasChanges()
                || expenseRequestSum.hasChanges();
    }

    String errorMessagesAsHtml() {
        // note: getErrorMessage() always return null before binder.validate()
        CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                expenseRequestSource.getErrorMessage(),
                expenseRequestCurrency.getErrorMessage(),
                expenseRequestConfirm.getErrorMessage(),
                expenseRequestPaid.getErrorMessage(),
                expenseRequestNumber.getErrorMessage(),
                expenseRequestSum.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    ExpenseRequestModel valueAsObject() {
        ExpenseRequestModel object = new ExpenseRequestModel();
        object.setId(expenseRequestId.valueAsUUID());
        object.setSource(expenseRequestSource.getValue());
        object.setCurrency(expenseRequestCurrency.getValue());
        object.setConfirm(expenseRequestConfirm.getValue());
        object.setPaid(expenseRequestPaid.getValue());
        object.setNumber(expenseRequestNumber.getValue());
        object.setSum(expenseRequestSum.getValue());

        return object;
    }
}
