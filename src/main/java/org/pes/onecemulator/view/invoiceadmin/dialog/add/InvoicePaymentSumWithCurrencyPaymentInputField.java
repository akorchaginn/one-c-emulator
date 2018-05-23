package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoicePaymentSumWithCurrencyPaymentInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentSumWithCurrencyPaymentInputField() {
        setCaption("Сумма факт в валюте платежа");
        setSizeFull();
        binder.bind(this, "paymentSumWithCurrencyPayment");
    }
}
