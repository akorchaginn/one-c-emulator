package org.pes.onecemulator.view.accountingentryadmin.dialog.add;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.AccountingEntryModel;

import java.util.List;

class AccountingEntryAddForm extends FormLayout {

    private final AccountingEntryCodeInputField accountingEntryCode;

    private final AccountingEntryDateInputField accountingEntryDate;

    private final AccountingEntryDocumentNameInputField accountingEntryDocumentName;

    private final AccountingEntryExpenseNumberInputField accountingEntryExpenseNumber;

    private final AccountingEntrySumInputField accountingEntrySum;

    AccountingEntryAddForm(List<String> expenseRequests) {
        this.accountingEntryCode = new AccountingEntryCodeInputField();
        this.accountingEntryDate = new AccountingEntryDateInputField();
        this.accountingEntryDocumentName = new AccountingEntryDocumentNameInputField();
        this.accountingEntryExpenseNumber = new AccountingEntryExpenseNumberInputField(expenseRequests);
        this.accountingEntrySum = new AccountingEntrySumInputField();
        addComponents(
                accountingEntryCode,
                accountingEntryDate,
                accountingEntryDocumentName,
                accountingEntryExpenseNumber,
                accountingEntrySum);
        setMargin(false);
    }

    void validate() {
        accountingEntryCode.binder.validate();
        accountingEntryDate.binder.validate();
        accountingEntryDocumentName.binder.validate();
        accountingEntryExpenseNumber.binder.validate();
        accountingEntrySum.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return accountingEntryCode.binder.isValid()
                && accountingEntryDate.binder.isValid()
                && accountingEntryDocumentName.binder.isValid()
                && accountingEntryExpenseNumber.binder.isValid()
                && accountingEntrySum.binder.isValid();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                accountingEntryCode.getErrorMessage(),
                accountingEntryDate.getErrorMessage(),
                accountingEntryDocumentName.getErrorMessage(),
                accountingEntryExpenseNumber.getErrorMessage(),
                accountingEntrySum.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    AccountingEntryModel valueAsObject() {
        final AccountingEntryModel object = new AccountingEntryModel();
        object.setCode(accountingEntryCode.getValue());
        object.setDate(accountingEntryDate.getValue());
        object.setDocumentName(accountingEntryDocumentName.getValue());
        object.setExpenseNumber(accountingEntryExpenseNumber.getValue());
        object.setSum(accountingEntrySum.getValue());

        return object;
    }
}
