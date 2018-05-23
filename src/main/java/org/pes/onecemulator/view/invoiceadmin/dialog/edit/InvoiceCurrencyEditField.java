package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

import java.math.BigDecimal;

public class InvoiceCurrencyEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceCurrencyEditField(String sum) {
        this.origin = sum;
        setValue(origin);
        setCaption("Валюта счёта");
        setSizeFull();
        binder.bind(this, "invoiceCurrency");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

    BigDecimal valueAsBigDecimal() {
        return new BigDecimal(getValue());
    }
}
