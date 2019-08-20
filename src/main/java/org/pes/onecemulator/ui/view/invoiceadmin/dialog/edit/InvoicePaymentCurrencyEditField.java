package org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoicePaymentCurrencyEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentCurrencyEditField(final String currency) {
        this.origin = currency;
        setValue(origin);
        setCaption("Валюта платежа");
        setSizeFull();
        binder.bind(this, "paymentCurrency");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
