package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoicePaymentSumRUBInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentSumRUBInputField() {
        setCaption("Сумма оплаты в рублях");
        setSizeFull();
        binder.bind(this, "paymentSumRUB");
    }
}
