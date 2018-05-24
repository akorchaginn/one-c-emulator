package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoicePaymentCurrencyEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentCurrencyEditField(String currency) {
        this.origin = currency;
        setValue(origin);
        setCaption("Валюта платежа");
        setSizeFull();
        binder.bind(this, "paymentCurrency");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
