package org.pes.onecemulator.view.expenserequestadmin.dialog.add;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;

class ExpenseRequestAddForm extends FormLayout {

    private final ExpenseRequestSourceInputField expenseRequestSource;

    private final ExpenseRequestCurrencyInputField expenseRequestCurrency;

    private final ExpenseRequestConfirmInputField expenseRequestConfirm;

    private final ExpenseRequestPaidInputField expenseRequestPaid;

    private final ExpenseRequestNumberInputField expenseRequestNumber;

    private final ExpenseRequestSumInputField expenseRequestSum;

    ExpenseRequestAddForm(List<String> sources) {
        this.expenseRequestSource = new ExpenseRequestSourceInputField(sources);
        this.expenseRequestCurrency = new ExpenseRequestCurrencyInputField();
        this.expenseRequestConfirm = new ExpenseRequestConfirmInputField();
        this.expenseRequestPaid = new ExpenseRequestPaidInputField();
        this.expenseRequestNumber = new ExpenseRequestNumberInputField();
        this.expenseRequestSum = new ExpenseRequestSumInputField();
        addComponents(
                expenseRequestNumber,
                expenseRequestSum,
                expenseRequestCurrency,
                expenseRequestPaid,
                expenseRequestConfirm,
                expenseRequestSource);
        setMargin(false);
    }

    void validate() {
        expenseRequestNumber.binder.validate();
        expenseRequestSum.binder.validate();
        expenseRequestCurrency.binder.validate();
        expenseRequestPaid.binder.validate();
        expenseRequestConfirm.binder.validate();
        expenseRequestSource.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return expenseRequestNumber.binder.isValid()
                && expenseRequestSum.binder.isValid()
                && expenseRequestCurrency.binder.isValid()
                && expenseRequestPaid.binder.isValid()
                && expenseRequestConfirm.binder.isValid()
                && expenseRequestSource.binder.isValid();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                expenseRequestNumber.getErrorMessage(),
                expenseRequestSum.getErrorMessage(),
                expenseRequestCurrency.getErrorMessage(),
                expenseRequestPaid.getErrorMessage(),
                expenseRequestConfirm.getErrorMessage(),
                expenseRequestSource.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    ExpenseRequestModel valueAsObject() {
        final ExpenseRequestModel object = new ExpenseRequestModel();
        object.setSource(expenseRequestSource.getValue());
        object.setCurrency(expenseRequestCurrency.getValue());
        object.setConfirm(expenseRequestConfirm.getValue());
        object.setPaid(expenseRequestPaid.getValue());
        object.setNumber(expenseRequestNumber.getValue());
        object.setSum(expenseRequestSum.getValue());

        return object;
    }
}
