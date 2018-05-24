package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

import java.math.BigDecimal;

public class InvoicePaymentSumEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePaymentSumEditField(String paymentSum) {
        this.origin = paymentSum;
        setValue(origin);
        setCaption("Сумма оплаты");
        setSizeFull();
        binder.bind(this, "paymentSum");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

    BigDecimal valueAsBigDecimal() {
        return new BigDecimal(getValue());
    }
}
