package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

class InvoiceAddForm extends FormLayout {

    private final InvoiceSourceInputField invoiceSource;

    private final InvoiceDateInputField invoiceDate;

    private final InvoiceNumberInputField invoiceNumber;

    private final InvoiceNumberOqInputField invoiceNumberOq;

    private final InvoicePayerInputField invoicePayer;

    private final InvoicePaymentDateInputField invoicePaymentDate;

    private final InvoicePaymentSumRUBInputField invoicePaymentSum;

    private final InvoiceStatusInputField invoiceStatus;

    private final InvoiceSumInputField invoiceSum;

    private final InvoiceExternalIdInputField invoiceExternalId;

    private final InvoiceCurrencyInputField invoiceCurrencyInputField;

    private final InvoicePaymentCurrencyInputField invoicePaymentCurrencyInputField;

    private final InvoicePaymentSumInputField invoicePaymentSumInputField;

    private final InvoiceSumRUBInputField invoiceSumRUBInputField;

    InvoiceAddForm(List<String> sources) {
        this.invoiceSource = new InvoiceSourceInputField(sources);
        this.invoiceDate = new InvoiceDateInputField();
        this.invoiceNumber = new InvoiceNumberInputField();
        this.invoiceNumberOq = new InvoiceNumberOqInputField();
        this.invoicePayer = new InvoicePayerInputField();
        this.invoicePaymentDate = new InvoicePaymentDateInputField();
        this.invoicePaymentSum = new InvoicePaymentSumRUBInputField();
        this.invoiceStatus = new InvoiceStatusInputField();
        this.invoiceSum = new InvoiceSumInputField();
        this.invoiceExternalId = new InvoiceExternalIdInputField();
        this.invoiceCurrencyInputField = new InvoiceCurrencyInputField();
        this.invoicePaymentCurrencyInputField = new InvoicePaymentCurrencyInputField();
        this.invoicePaymentSumInputField = new InvoicePaymentSumInputField();
        this.invoiceSumRUBInputField = new InvoiceSumRUBInputField();


        addComponents(
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
                invoiceSumRUBInputField,
                invoicePaymentCurrencyInputField,
                invoiceCurrencyInputField,
                invoiceSumRUBInputField,
                invoicePaymentSumInputField);
        setMargin(false);
    }

    InvoiceSourceInputField getInvoiceSource() {
        return invoiceSource;
    }

    InvoicePayerInputField getInvoicePayer() {
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
        invoiceSumRUBInputField.binder.validate();
        invoicePaymentCurrencyInputField.binder.validate();
        invoiceCurrencyInputField.binder.validate();
        invoiceSumRUBInputField.binder.validate();
        invoicePaymentSumInputField.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return invoiceNumber.binder.isValid();
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
                invoiceSumRUBInputField.getErrorMessage(),
                invoicePaymentCurrencyInputField.getErrorMessage(),
                invoiceCurrencyInputField.getErrorMessage(),
                invoiceSumRUBInputField.getErrorMessage(),
                invoicePaymentSumInputField.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    InvoiceModel valueAsObject() {
        final InvoiceModel object = new InvoiceModel();
        object.setSource(invoiceSource.getValue());
        object.setDate(invoiceDate.getValue());
        object.setNumber(invoiceNumber.getValue());
        object.setNumberOq(invoiceNumberOq.getValue());
        object.setPayerCode(invoicePayer.getValue());
        object.setPaymentDate(invoicePaymentDate.getValue());
        object.setPaymentSum(invoiceSum.getValue());
        object.setStatus(invoiceStatus.getValue());
        object.setSum(invoiceSum.getValue());
        object.setExternalId(invoiceExternalId.getValue());
        object.setCurrency(invoiceCurrencyInputField.getValue());
        object.setSumRUB(invoiceSumRUBInputField.getValue());
        object.setPaymentCurrency(invoicePaymentCurrencyInputField.getValue());
        object.setPaymentSum(invoicePaymentSumInputField.getValue());

        return object;
    }
}
