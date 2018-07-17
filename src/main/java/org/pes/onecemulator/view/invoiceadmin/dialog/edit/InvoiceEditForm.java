package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public class InvoiceEditForm extends FormLayout {

    private InvoiceIdReadOnlyField invoiceId;

    private InvoiceSourceEditField invoiceSource;

    private InvoiceDateEditField invoiceDate;

    private InvoiceNumberEditField invoiceNumber;

    private InvoiceNumberOqEditField invoiceNumberOq;

    private InvoicePayerEditField invoicePayer;

    private InvoicePaymentDateEditField invoicePaymentDate;

    private InvoicePaymentSumEditField invoicePaymentSum;

    private InvoiceStatusEditField invoiceStatus;

    private InvoiceSumEditField invoiceSum;

    private InvoiceExternalIdEditField invoiceExternalId;

    private InvoiceSumRubEditField invoiceSumRubEditField;

    private InvoicePaymentSumRUBEditField invoicePaymentSumRUBEditField;

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
        this.invoicePaymentSum = new InvoicePaymentSumEditField(target.getPaymentSumRUB());
        this.invoiceStatus = new InvoiceStatusEditField(target.getStatus());
        this.invoiceSum = new InvoiceSumEditField(target.getSum());
        this.invoiceExternalId = new InvoiceExternalIdEditField(target.getExternalId());
        this.invoiceSumRubEditField = new InvoiceSumRubEditField(target.getSumRUB());
        this.invoiceCurrencyEditField = new InvoiceCurrencyEditField(target.getCurrency());
        this.invoicePaymentSumRUBEditField = new InvoicePaymentSumRUBEditField(target.getPaymentSum());
        this.invoicePaymentCurrencyEditField = new InvoicePaymentCurrencyEditField(target.getPaymentCurrency());
        //this.originalVersion = targetSummary.audit().version();

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
                invoicePaymentSumRUBEditField,
                invoiceSumRubEditField);
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
        invoicePaymentSumRUBEditField.binder.validate();
        invoiceSumRubEditField.binder.validate();
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
                && invoicePaymentSumRUBEditField.binder.isValid()
                && invoiceSumRubEditField.binder.isValid();
    }

    boolean hasChanges() {
        // note: binder.setBean() and binder.hasChange() are not work I expected
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
                || invoicePaymentSumRUBEditField.hasChanges()
                || invoiceSumRubEditField.hasChanges();
    }

    String errorMessagesAsHtml() {
        // note: getErrorMessage() always return null before binder.validate()
        CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
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
                invoicePaymentSumRUBEditField.getErrorMessage(),
                invoiceSumRubEditField.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    InvoiceModel valueAsObject() {
        InvoiceModel object = new InvoiceModel();
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
        object.setPaymentSum(invoicePaymentSumRUBEditField.getValue());
        object.setPaymentSumRUB(invoicePaymentSum.getValue());
        object.setPaymentCurrency(invoicePaymentCurrencyEditField.getValue());
        object.setCurrency(invoiceCurrencyEditField.getValue());
        object.setSumRUB(invoiceSumRubEditField.getValue());

        return object;
    }
}
