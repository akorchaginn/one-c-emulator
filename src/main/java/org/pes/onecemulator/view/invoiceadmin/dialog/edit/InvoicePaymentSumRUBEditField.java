package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoicePaymentSumRUBEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentSumRUBEditField(String paymentSum) {
        this.origin = paymentSum;
        setValue(origin);
        setCaption("Сумма оплаты");
        setSizeFull();
        binder.bind(this, "paymentSumRUB");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

}
