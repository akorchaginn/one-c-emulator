package org.pes.onecemulator.view.invoiceadmin.dialog.add.view;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public class InvoiceAddForm extends FormLayout {

    private final InvoiceSourceInputField invoiceSource;

    private final InvoiceDateInputField invoiceDate;

    private final InvoiceNumberInputField invoiceNumber;

    private final InvoiceNumberOqInputField invoiceNumberOq;

    private final InvoicePayerInputField invoicePayer;

    private final InvoicePaymentDateInputField invoicePaymentDate;

    private final InvoicePaymentSumInputField invoicePaymentSum;

    private final InvoiceStatusInputField invoiceStatus;

    private final InvoiceSumInputField invoiceSum;

    private final InvoiceExternalIdInputField invoiceExternalId;

    //Version originalVersion;

    InvoiceAddForm(List<String> sources, List<String> payers) {
        this.invoiceSource = new InvoiceSourceInputField(sources);
        this.invoiceDate = new InvoiceDateInputField();
        this.invoiceNumber = new InvoiceNumberInputField();
        this.invoiceNumberOq = new InvoiceNumberOqInputField();
        this.invoicePayer = new InvoicePayerInputField(payers);
        this.invoicePaymentDate = new InvoicePaymentDateInputField();
        this.invoicePaymentSum = new InvoicePaymentSumInputField();
        this.invoiceStatus = new InvoiceStatusInputField();
        this.invoiceSum = new InvoiceSumInputField();
        this.invoiceExternalId = new InvoiceExternalIdInputField();
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
                invoiceExternalId);
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
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return invoiceNumber.binder.isValid();
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
                invoiceExternalId.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    InvoiceModel valueAsObject() {
        InvoiceModel object = new InvoiceModel();
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

        return object;
    }
}
