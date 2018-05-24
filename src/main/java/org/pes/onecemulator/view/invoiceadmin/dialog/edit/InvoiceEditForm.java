package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public class InvoiceEditForm extends FormLayout {

    private final InvoiceIdReadOnlyField invoiceId;

    final InvoiceSourceEditField invoiceSource;

    private final InvoiceDateEditField invoiceDate;

    private final InvoiceNumberEditField invoiceNumber;

    private final InvoiceNumberOqEditField invoiceNumberOq;

    final InvoicePayerEditField invoicePayer;

    private final InvoicePaymentDateEditField invoicePaymentDate;

    private final InvoicePaymentSumEditField invoicePaymentSum;

    private final InvoiceStatusEditField invoiceStatus;

    private final InvoiceSumEditField invoiceSum;

    private final InvoiceExternalIdEditField invoiceExternalId;

    private final InvoiceSumRubEditField invoiceSumRubEditField;

    private final InvoicePaymentSumWithCurrencyPaymentEditField invoicePaymentSumWithCurrencyPaymentEditField;

    private final InvoiceCurrencyEditField invoiceCurrencyEditField;

    private final InvoicePaymentCurrencyEditField invoicePaymentCurrencyEditField;

    //Version originalVersion;

    InvoiceEditForm(InvoiceModel target, List<String> sources) {
        this.invoiceId = new InvoiceIdReadOnlyField(target.getId());
        this.invoiceSource = new InvoiceSourceEditField(target.getSource(), sources);
        this.invoiceDate = new InvoiceDateEditField(target.getDate());
        this.invoiceNumber = new InvoiceNumberEditField(target.getNumber());
        this.invoiceNumberOq = new InvoiceNumberOqEditField(target.getNumberOq());
        this.invoicePayer = new InvoicePayerEditField(target.getPayerCode());
        this.invoicePaymentDate = new InvoicePaymentDateEditField(target.getPaymentDate());
        this.invoicePaymentSum = new InvoicePaymentSumEditField(target.getPaymentSum());
        this.invoiceStatus = new InvoiceStatusEditField(target.getStatus());
        this.invoiceSum = new InvoiceSumEditField(target.getInvoiceSum());
        this.invoiceExternalId = new InvoiceExternalIdEditField(target.getExternalId());
        this.invoiceSumRubEditField = new InvoiceSumRubEditField(target.getInvoiceSumRUB());
        this.invoiceCurrencyEditField = new InvoiceCurrencyEditField(target.getInvoiceCurrency());
        this.invoicePaymentSumWithCurrencyPaymentEditField = new InvoicePaymentSumWithCurrencyPaymentEditField(target.getPaymentSumWithCurrencyPayment());
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
                invoicePaymentSumWithCurrencyPaymentEditField,
                invoiceSumRubEditField);
        setMargin(false);
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
        invoicePaymentSumWithCurrencyPaymentEditField.binder.validate();
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
                && invoicePaymentSumWithCurrencyPaymentEditField.binder.isValid()
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
                || invoicePaymentSumWithCurrencyPaymentEditField.hasChanges()
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
                invoicePaymentSumWithCurrencyPaymentEditField.getErrorMessage(),
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
        object.setPaymentSum(invoicePaymentSum.getValue());
        object.setStatus(invoiceStatus.getValue());
        object.setInvoiceSum(invoiceSum.getValue());
        object.setExternalId(invoiceExternalId.getValue());
        object.setPaymentSumWithCurrencyPayment(invoicePaymentSumWithCurrencyPaymentEditField.getValue());
        object.setPaymentSum(invoicePaymentSum.getValue());
        object.setPaymentCurrency(invoicePaymentCurrencyEditField.getValue());
        object.setInvoiceCurrency(invoiceCurrencyEditField.getValue());
        object.setInvoiceSumRUB(invoiceSumRubEditField.getValue());

        return object;
    }
}
