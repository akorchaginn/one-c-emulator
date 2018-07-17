package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

import java.math.BigDecimal;

class InvoicePaymentSumRUBEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentSumRUBEditField(String sum) {
        this.origin = sum;
        setValue(origin);
        setCaption("Сумма платежа в валюте платежа");
        setSizeFull();
        binder.bind(this, "paymentSumWithCurrencyPayment");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

}
