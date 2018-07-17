package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

class InvoiceEditForm extends FormLayout {

    private InvoiceIdReadOnlyField invoiceId;

    private InvoiceSourceEditField invoiceSource;

    private InvoiceDateEditField invoiceDate;

    private InvoiceNumberEditField invoiceNumber;

    private InvoiceNumberOqEditField invoiceNumberOq;

    private InvoicePayerEditField invoicePayer;

    private InvoicePaymentDateEditField invoicePaymentDate;

    private InvoicePaymentSumRUBEditField invoicePaymentSum;

    private InvoiceStatusEditField invoiceStatus;

    private InvoiceSumEditField invoiceSum;

    private InvoiceExternalIdEditField invoiceExternalId;

    private InvoiceSumRUBEditField invoiceSumRUBEditField;

    private InvoicePaymentSumEditField invoicePaymentSumEditField;

    private InvoiceCurrencyEditField invoiceCurrencyEditField;

    private InvoicePaymentCurrencyEditField invoicePaymentCurrencyEditField;

    InvoiceEditForm(InvoiceModel target, List<String> sources) {
        this.invoiceId = new InvoiceIdReadOnlyField(target.getId());
        this.invoiceSource = new InvoiceSourceEditField(target.getSource(), sources);
        this.invoiceDate = new InvoiceDateEditField(target.getDate());
        this.invoiceNumber = new InvoiceNumberEditField(target.getNumber());
        this.invoiceNumberOq = new InvoiceNumberOqEditField(target.getNumberOq());
        this.invoicePayer = new InvoicePayerEditField(target.getPayerCode());
        this.invoicePaymentDate = new InvoicePaymentDateEditField(target.getPaymentDate());
        this.invoicePaymentSum = new InvoicePaymentSumRUBEditField(target.getPaymentSumRUB());
        this.invoiceStatus = new InvoiceStatusEditField(target.getStatus());
        this.invoiceSum = new InvoiceSumEditField(target.getSum());
        this.invoiceExternalId = new InvoiceExternalIdEditField(target.getExternalId());
        this.invoiceSumRUBEditField = new InvoiceSumRUBEditField(target.getSumRUB());
        this.invoiceCurrencyEditField = new InvoiceCurrencyEditField(target.getCurrency());
        this.invoicePaymentSumEditField = new InvoicePaymentSumEditField(target.getPaymentSum());
        this.invoicePaymentCurrencyEditField = new InvoicePaymentCurrencyEditField(target.getPaymentCurrency());

        addComponents(
                invoiceId,
                invoiceSource,
                invoiceDate,
                invoiceNumber,
                invoiceNumberOq,
                invoicePayer,
                invoicePaymentDate,
                invoicePaymentSum,
                invoiceStatus,
                invoiceSum,
                invoiceExternalId,
                invoiceCurrencyEditField,
                invoicePaymentCurrencyEditField,
                invoicePaymentSumEditField,
                invoiceSumRUBEditField);
        setMargin(false);
    }

    InvoiceSourceEditField getInvoiceSource() {
        return invoiceSource;
    }

    InvoicePayerEditField getInvoicePayer() {
        return invoicePayer;
    }

    void validate() {
        invoiceSource.binder.validate();
        invoiceDate.binder.validate();
        invoiceNumber.binder.validate();
        invoiceNumberOq.binder.validate();
        invoicePayer.binder.validate();
        invoicePaymentDate.binder.validate();
        invoicePaymentSum.binder.validate();
        invoiceStatus.binder.validate();
        invoiceSum.binder.validate();
        invoiceExternalId.binder.validate();
        invoiceCurrencyEditField.binder.validate();
        invoicePaymentCurrencyEditField.binder.validate();
        invoicePaymentSumEditField.binder.validate();
        invoiceSumRUBEditField.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return invoiceSource.binder.isValid()
                && invoiceDate.binder.isValid()
                && invoiceNumber.binder.isValid()
                && invoiceNumberOq.binder.isValid()
                && invoicePayer.binder.isValid()
                && invoicePaymentDate.binder.isValid()
                && invoicePaymentSum.binder.isValid()
                && invoiceStatus.binder.isValid()
                && invoiceSum.binder.isValid()
                && invoiceExternalId.binder.isValid()
                && invoiceCurrencyEditField.binder.isValid()
                && invoicePaymentCurrencyEditField.binder.isValid()
                && invoicePaymentSumEditField.binder.isValid()
                && invoiceSumRUBEditField.binder.isValid();
    }

    boolean hasChanges() {
        return invoiceSource.hasChanges()
                || invoiceDate.hasChanges()
                || invoiceNumber.hasChanges()
                || invoiceNumberOq.hasChanges()
                || invoicePayer.hasChanges()
                || invoicePaymentDate.hasChanges()
                || invoicePaymentSum.hasChanges()
                || invoiceStatus.hasChanges()
                || invoiceSum.hasChanges()
                || invoiceExternalId.hasChanges()
                || invoiceCurrencyEditField.hasChanges()
                || invoicePaymentCurrencyEditField.hasChanges()
                || invoicePaymentSumEditField.hasChanges()
                || invoiceSumRUBEditField.hasChanges();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                invoiceSource.getErrorMessage(),
                invoiceDate.getErrorMessage(),
                invoiceNumber.getErrorMessage(),
                invoiceNumberOq.getErrorMessage(),
                invoicePayer.getErrorMessage(),
                invoicePaymentDate.getErrorMessage(),
                invoicePaymentSum.getErrorMessage(),
                invoiceStatus.getErrorMessage(),
                invoiceSum.getErrorMessage(),
                invoiceExternalId.getErrorMessage(),
                invoiceCurrencyEditField.getErrorMessage(),
                invoicePaymentCurrencyEditField.getErrorMessage(),
                invoicePaymentSumEditField.getErrorMessage(),
                invoiceSumRUBEditField.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    InvoiceModel valueAsObject() {
        final InvoiceModel object = new InvoiceModel();
        object.setId(invoiceId.valueAsUUID());
        object.setSource(invoiceSource.getValue());
        object.setDate(invoiceDate.getValue());
        object.setNumber(invoiceNumber.getValue());
        object.setNumberOq(invoiceNumberOq.getValue());
        object.setPayerCode(invoicePayer.getValue());
        object.setPaymentDate(invoicePaymentDate.getValue());
        object.setPaymentSumRUB(invoicePaymentSum.getValue());
        object.setStatus(invoiceStatus.getValue());
        object.setSum(invoiceSum.getValue());
        object.setExternalId(invoiceExternalId.getValue());
        object.setPaymentSum(invoicePaymentSumEditField.getValue());
        object.setPaymentSumRUB(invoicePaymentSum.getValue());
        object.setPaymentCurrency(invoicePaymentCurrencyEditField.getValue());
        object.setCurrency(invoiceCurrencyEditField.getValue());
        object.setSumRUB(invoiceSumRUBEditField.getValue());

        return object;
    }
}
