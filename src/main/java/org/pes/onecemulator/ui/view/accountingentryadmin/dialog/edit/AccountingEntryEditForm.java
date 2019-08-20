package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.util.List;

class AccountingEntryEditForm extends FormLayout {

    private final AccountingEntryIdReadOnlyField accountingEntryId;

    private final AccountingEntryCodeEditField accountingEntryCode;

    private final AccountingEntryDateEditField accountingEntryDate;

    private final AccountingEntryDocumentNameEditField accountingEntryDocumentName;

    private final AccountingEntryExpenseNumberEditField accountingEntryExpenseNumber;

    private final AccountingEntrySumEditField accountingEntrySum;

    AccountingEntryEditForm(AccountingEntryModel target, List<String> expenseRequests) {
        this.accountingEntryId = new AccountingEntryIdReadOnlyField(target.getId());
        this.accountingEntryCode = new AccountingEntryCodeEditField(target.getCode());
        this.accountingEntryDate = new AccountingEntryDateEditField(target.getDate());
        this.accountingEntryDocumentName = new AccountingEntryDocumentNameEditField(target.getDocumentName());
        this.accountingEntryExpenseNumber = new AccountingEntryExpenseNumberEditField(target.getExpenseNumber(), expenseRequests);
        this.accountingEntrySum = new AccountingEntrySumEditField(target.getSum());
        addComponents(
                accountingEntryId,
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

    boolean hasChanges() {
        // note: binder.setBean() and binder.hasChange() are not work I expected
        return accountingEntryCode.hasChanges()
                || accountingEntryDate.hasChanges()
                || accountingEntryDocumentName.hasChanges()
                || accountingEntryExpenseNumber.hasChanges()
                || accountingEntrySum.hasChanges();
    }

    String errorMessagesAsHtml() {
        // note: getErrorMessage() always return null before binder.validate()
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
        object.setId(accountingEntryId.valueAsUUID());
        object.setCode(accountingEntryCode.getValue());
        object.setDate(accountingEntryDate.getValue());
        object.setDocumentName(accountingEntryDocumentName.getValue());
        object.setExpenseNumber(accountingEntryExpenseNumber.getValue());
        object.setSum(accountingEntrySum.getValue());

        return object;
    }
}
