package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoicePaymentSumEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentSumEditField(final String sum) {
        this.origin = sum;
        setValue(origin);
        setCaption("Сумма платежа в валюте платежа");
        setSizeFull();
        binder.bind(this, "paymentSum");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }

}
