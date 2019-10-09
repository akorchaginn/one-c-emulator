package org.pes.onecemulator.ui.view.payeradmin.dialog.add;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.List;

class PayerAddForm extends FormLayout {

    private final PayerCodeInputField payerCode;

    private final PayerNameInputField payerName;

    private final PayerFullNameInputField payerFullName;

    private final PayerInnInputField payerInn;

    private final PayerKppInputField payerKpp;

    private final PayerAddressInputField payerAddress;

    private final PayerCustomerInputField payerCustomer;

    private final PayerSubcontractorInputField payerSubcontractor;

    private final PayerSourceInputField payerSource;

    PayerAddForm(List<String> sources) {
        this.payerCode = new PayerCodeInputField();
        this.payerName = new PayerNameInputField();
        this.payerFullName = new PayerFullNameInputField();
        this.payerInn = new PayerInnInputField();
        this.payerKpp = new PayerKppInputField();
        this.payerAddress = new PayerAddressInputField();
        this.payerSubcontractor = new PayerSubcontractorInputField();
        this.payerCustomer = new PayerCustomerInputField();
        this.payerSource = new PayerSourceInputField(sources);
        addComponents(payerCode, payerName, payerFullName, payerInn, payerKpp, payerAddress, payerSubcontractor, payerCustomer, payerSource);
        setMargin(false);
    }

    void validate() {
        payerCode.binder.validate();
        payerName.binder.validate();
        payerFullName.binder.validate();
        payerInn.binder.validate();
        payerKpp.binder.validate();
        payerSubcontractor.binder.validate();
        payerCustomer.binder.validate();
        payerAddress.binder.validate();
        payerSource.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return payerCode.binder.isValid()
                && payerName.binder.isValid()
                && payerFullName.binder.isValid()
                && payerInn.binder.isValid()
                && payerKpp.binder.isValid()
                && payerAddress.binder.isValid()
                && payerSubcontractor.binder.isValid()
                && payerCustomer.binder.isValid()
                && payerSource.binder.isValid();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                payerCode.getErrorMessage(),
                payerName.getErrorMessage(),
                payerFullName.getErrorMessage(),
                payerInn.getErrorMessage(),
                payerKpp.getErrorMessage(),
                payerAddress.getErrorMessage(),
                payerSubcontractor.getErrorMessage(),
                payerCustomer.getErrorMessage(),
                payerSource.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    PayerModel valueAsObject() {
        final PayerModel object = new PayerModel();
        object.setCode(payerCode.getValue());
        object.setName(payerName.getValue());
        object.setFullName(payerFullName.getValue());
        object.setInn(payerInn.getValue());
        object.setKpp(payerKpp.getValue());
        object.setAddress(payerAddress.getValue());
        object.setSubcontractor(payerSubcontractor.getValue());
        object.setCustomer(payerCustomer.getValue());
        object.getSources().addAll(payerSource.getValue());

        return object;
    }
}
