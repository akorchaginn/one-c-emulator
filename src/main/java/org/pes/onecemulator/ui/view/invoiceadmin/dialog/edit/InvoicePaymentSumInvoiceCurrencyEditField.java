package org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoicePaymentSumInvoiceCurrencyEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentSumInvoiceCurrencyEditField(final String paymentSumInvoiceCurrency) {
        this.origin = paymentSumInvoiceCurrency;
        setValue(origin);
        setCaption("Сумма оплаты в валюте счёта");
        setSizeFull();
        binder.bind(this, "paymentSumInvoiceCurrency");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }

}
