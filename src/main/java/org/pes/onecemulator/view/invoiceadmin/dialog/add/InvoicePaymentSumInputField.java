package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoicePaymentSumInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentSumInputField() {
        setCaption("Сумма оплаты в валюте платежа");
        setSizeFull();
        binder.bind(this, "paymentSum");
    }
}
