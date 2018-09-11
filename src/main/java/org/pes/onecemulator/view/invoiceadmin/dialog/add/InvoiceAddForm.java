package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

class InvoiceAddForm extends FormLayout {

    private final InvoiceSourceInputField invoiceSourceInputField;

    private final InvoiceDateInputField invoiceDateInputField;

    private final InvoiceNumberInputField invoiceNumberInputField;

    private final InvoiceNumberOqInputField invoiceNumberOqInputField;

    private final InvoicePayerInputField invoicePayerInputField;

    private final InvoicePaymentDateInputField invoicePaymentDateInputField;

    private final InvoicePaymentSumRUBInputField invoicePaymentSumRUBInputField;

    private final InvoiceStatusInputField invoiceStatusInputField;

    private final InvoiceSumInputField invoiceSumInputField;

    private final InvoiceExternalIdInputField invoiceExternalIdInputField;

    private final InvoiceCurrencyInputField invoiceCurrencyInputField;

    private final InvoicePaymentCurrencyInputField invoicePaymentCurrencyInputField;

    private final InvoicePaymentSumInputField invoicePaymentSumInputField;

    private final InvoiceSumRUBInputField invoiceSumRUBInputField;

    InvoiceAddForm(List<String> sources) {
        this.invoiceSourceInputField = new InvoiceSourceInputField(sources);
        this.invoiceDateInputField = new InvoiceDateInputField();
        this.invoiceNumberInputField = new InvoiceNumberInputField();
        this.invoiceNumberOqInputField = new InvoiceNumberOqInputField();
        this.invoicePayerInputField = new InvoicePayerInputField();
        this.invoicePaymentDateInputField = new InvoicePaymentDateInputField();
        this.invoicePaymentSumRUBInputField = new InvoicePaymentSumRUBInputField();
        this.invoiceStatusInputField = new InvoiceStatusInputField();
        this.invoiceSumInputField = new InvoiceSumInputField();
        this.invoiceExternalIdInputField = new InvoiceExternalIdInputField();
        this.invoiceCurrencyInputField = new InvoiceCurrencyInputField();
        this.invoicePaymentCurrencyInputField = new InvoicePaymentCurrencyInputField();
        this.invoicePaymentSumInputField = new InvoicePaymentSumInputField();
        this.invoiceSumRUBInputField = new InvoiceSumRUBInputField();


        addComponents(
                invoiceSourceInputField,
                invoiceDateInputField,
                invoiceNumberInputField,
                invoiceNumberOqInputField,
                invoicePayerInputField,
                invoicePaymentDateInputField,
                invoicePaymentSumRUBInputField,
                invoiceStatusInputField,
                invoiceSumInputField,
                invoiceExternalIdInputField,
                invoiceSumRUBInputField,
                invoicePaymentCurrencyInputField,
                invoiceCurrencyInputField,
                invoicePaymentSumInputField);
        setMargin(false);
    }

    InvoiceSourceInputField getInvoiceSource() {
        return invoiceSourceInputField;
    }

    InvoicePayerInputField getInvoicePayer() {
        return invoicePayerInputField;
    }

    void validate() {
        invoiceSourceInputField.binder.validate();
        invoiceDateInputField.binder.validate();
        invoiceNumberInputField.binder.validate();
        invoiceNumberOqInputField.binder.validate();
        invoicePayerInputField.binder.validate();
        invoicePaymentDateInputField.binder.validate();
        invoicePaymentSumRUBInputField.binder.validate();
        invoiceStatusInputField.binder.validate();
        invoiceSumInputField.binder.validate();
        invoiceExternalIdInputField.binder.validate();
        invoiceSumRUBInputField.binder.validate();
        invoicePaymentCurrencyInputField.binder.validate();
        invoiceCurrencyInputField.binder.validate();
        invoicePaymentSumInputField.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return invoiceNumberInputField.binder.isValid();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                invoiceSourceInputField.getErrorMessage(),
                invoiceDateInputField.getErrorMessage(),
                invoiceNumberInputField.getErrorMessage(),
                invoiceNumberOqInputField.getErrorMessage(),
                invoicePayerInputField.getErrorMessage(),
                invoicePaymentDateInputField.getErrorMessage(),
                invoicePaymentSumRUBInputField.getErrorMessage(),
                invoiceStatusInputField.getErrorMessage(),
                invoiceSumInputField.getErrorMessage(),
                invoiceExternalIdInputField.getErrorMessage(),
                invoiceSumRUBInputField.getErrorMessage(),
                invoicePaymentCurrencyInputField.getErrorMessage(),
                invoiceCurrencyInputField.getErrorMessage(),
                invoicePaymentSumInputField.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    InvoiceModel valueAsObject() {
        final InvoiceModel object = new InvoiceModel();
        object.setSource(invoiceSourceInputField.getValue());
        object.setDate(invoiceDateInputField.getValue());
        object.setNumber(invoiceNumberInputField.getValue());
        object.setNumberOq(invoiceNumberOqInputField.getValue());
        object.setPayerCode(invoicePayerInputField.getValue());
        object.setPaymentDate(invoicePaymentDateInputField.getValue());
        object.setPaymentSumRUB(invoicePaymentSumRUBInputField.getValue());
        object.setStatus(invoiceStatusInputField.getValue());
        object.setSum(invoiceSumInputField.getValue());
        object.setExternalId(invoiceExternalIdInputField.getValue());
        object.setCurrency(invoiceCurrencyInputField.getValue());
        object.setSumRUB(invoiceSumRUBInputField.getValue());
        object.setPaymentCurrency(invoicePaymentCurrencyInputField.getValue());
        object.setPaymentSum(invoicePaymentSumInputField.getValue());

        return object;
    }
}
