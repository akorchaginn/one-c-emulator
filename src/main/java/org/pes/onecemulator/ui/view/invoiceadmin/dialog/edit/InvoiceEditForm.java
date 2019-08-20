package org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.util.List;

class InvoiceEditForm extends FormLayout {

    private InvoiceIdReadOnlyField invoiceId;

    private InvoiceSourceEditField invoiceSourceEditField;

    private InvoiceDateEditField invoiceDateEditField;

    private InvoiceNumberEditField invoiceNumberEditField;

    private InvoicePayerEditField invoicePayerEditField;

    private InvoicePaymentDateEditField invoicePaymentDateEditField;

    private InvoicePaymentSumInvoiceCurrencyEditField invoicePaymentSumInvoiceCurrencyEditField;

    private InvoiceStatusEditField invoiceStatusEditField;

    private InvoiceSumEditField invoiceSumEditField;

    private InvoiceExternalIdEditField invoiceExternalIdEditField;

    private InvoicePaymentSumEditField invoicePaymentSumEditField;

    private InvoiceCurrencyEditField invoiceCurrencyEditField;

    private InvoicePaymentCurrencyEditField invoicePaymentCurrencyEditField;

    InvoiceEditForm(InvoiceModel target, List<String> sources) {
        this.invoiceId = new InvoiceIdReadOnlyField(target.getId());
        this.invoiceSourceEditField = new InvoiceSourceEditField(target.getSource(), sources);
        this.invoiceDateEditField = new InvoiceDateEditField(target.getDate());
        this.invoiceNumberEditField = new InvoiceNumberEditField(target.getNumber());
        this.invoicePayerEditField = new InvoicePayerEditField(target.getPayerCode());
        this.invoicePaymentDateEditField = new InvoicePaymentDateEditField(target.getPaymentDate());
        this.invoicePaymentSumInvoiceCurrencyEditField = new InvoicePaymentSumInvoiceCurrencyEditField(target.getPaymentSumInvoiceCurrency());
        this.invoiceStatusEditField = new InvoiceStatusEditField(target.getStatus());
        this.invoiceSumEditField = new InvoiceSumEditField(target.getSum());
        this.invoiceExternalIdEditField = new InvoiceExternalIdEditField(target.getExternalId());
        this.invoiceCurrencyEditField = new InvoiceCurrencyEditField(target.getCurrency());
        this.invoicePaymentSumEditField = new InvoicePaymentSumEditField(target.getPaymentSum());
        this.invoicePaymentCurrencyEditField = new InvoicePaymentCurrencyEditField(target.getPaymentCurrency());

        addComponents(
                invoiceId,
                invoiceSourceEditField,
                invoiceDateEditField,
                invoiceNumberEditField,
                invoicePayerEditField,
                invoicePaymentDateEditField,
                invoicePaymentSumInvoiceCurrencyEditField,
                invoiceStatusEditField,
                invoiceSumEditField,
                invoiceExternalIdEditField,
                invoicePaymentCurrencyEditField,
                invoiceCurrencyEditField,
                invoicePaymentSumEditField);
        setMargin(false);
    }

    InvoiceSourceEditField getInvoiceSource() {
        return invoiceSourceEditField;
    }

    InvoicePayerEditField getInvoicePayer() {
        return invoicePayerEditField;
    }

    void validate() {
        invoiceSourceEditField.binder.validate();
        invoiceDateEditField.binder.validate();
        invoiceNumberEditField.binder.validate();
        invoicePayerEditField.binder.validate();
        invoicePaymentDateEditField.binder.validate();
        invoicePaymentSumInvoiceCurrencyEditField.binder.validate();
        invoiceStatusEditField.binder.validate();
        invoiceSumEditField.binder.validate();
        invoiceExternalIdEditField.binder.validate();
        invoiceCurrencyEditField.binder.validate();
        invoicePaymentCurrencyEditField.binder.validate();
        invoicePaymentSumEditField.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return invoiceSourceEditField.binder.isValid()
                && invoiceDateEditField.binder.isValid()
                && invoiceNumberEditField.binder.isValid()
                && invoicePayerEditField.binder.isValid()
                && invoicePaymentDateEditField.binder.isValid()
                && invoicePaymentSumInvoiceCurrencyEditField.binder.isValid()
                && invoiceStatusEditField.binder.isValid()
                && invoiceSumEditField.binder.isValid()
                && invoiceExternalIdEditField.binder.isValid()
                && invoiceCurrencyEditField.binder.isValid()
                && invoicePaymentCurrencyEditField.binder.isValid()
                && invoicePaymentSumEditField.binder.isValid();
    }

    boolean hasChanges() {
        return invoiceSourceEditField.hasChanges()
                || invoiceDateEditField.hasChanges()
                || invoiceNumberEditField.hasChanges()
                || invoicePayerEditField.hasChanges()
                || invoicePaymentDateEditField.hasChanges()
                || invoicePaymentSumInvoiceCurrencyEditField.hasChanges()
                || invoiceStatusEditField.hasChanges()
                || invoiceSumEditField.hasChanges()
                || invoiceExternalIdEditField.hasChanges()
                || invoiceCurrencyEditField.hasChanges()
                || invoicePaymentCurrencyEditField.hasChanges()
                || invoicePaymentSumEditField.hasChanges();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                invoiceSourceEditField.getErrorMessage(),
                invoiceDateEditField.getErrorMessage(),
                invoiceNumberEditField.getErrorMessage(),
                invoicePayerEditField.getErrorMessage(),
                invoicePaymentDateEditField.getErrorMessage(),
                invoicePaymentSumInvoiceCurrencyEditField.getErrorMessage(),
                invoiceStatusEditField.getErrorMessage(),
                invoiceSumEditField.getErrorMessage(),
                invoiceExternalIdEditField.getErrorMessage(),
                invoiceCurrencyEditField.getErrorMessage(),
                invoicePaymentCurrencyEditField.getErrorMessage(),
                invoicePaymentSumEditField.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    InvoiceModel valueAsObject() {
        final InvoiceModel object = new InvoiceModel();
        object.setId(invoiceId.valueAsUUID());
        object.setSource(invoiceSourceEditField.getValue());
        object.setDate(invoiceDateEditField.getValue());
        object.setNumber(invoiceNumberEditField.getValue());
        object.setPayerCode(invoicePayerEditField.getValue());
        object.setPaymentDate(invoicePaymentDateEditField.getValue());
        object.setPaymentSumInvoiceCurrency(invoicePaymentSumInvoiceCurrencyEditField.getValue());
        object.setStatus(invoiceStatusEditField.getValue());
        object.setSum(invoiceSumEditField.getValue());
        object.setExternalId(invoiceExternalIdEditField.getValue());
        object.setPaymentSum(invoicePaymentSumEditField.getValue());
        object.setPaymentCurrency(invoicePaymentCurrencyEditField.getValue());
        object.setCurrency(invoiceCurrencyEditField.getValue());

        return object;
    }
}
