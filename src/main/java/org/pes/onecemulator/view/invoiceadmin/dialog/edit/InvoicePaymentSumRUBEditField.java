package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoicePaymentSumRUBEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentSumRUBEditField(final String paymentSumRUB) {
        this.origin = paymentSumRUB;
        setValue(origin);
        setCaption("Сумма оплаты в рублях");
        setSizeFull();
        binder.bind(this, "paymentSumRUB");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }

}
