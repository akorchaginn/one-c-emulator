package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoicePaymentSumRUBInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentSumRUBInputField() {
        setCaption("Сумма оплаты");
        setSizeFull();
        binder.bind(this, "paymentSumRUB");
    }
}
