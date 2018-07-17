package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoiceCurrencyEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceCurrencyEditField(String currency) {
        this.origin = currency;
        setValue(origin);
        setCaption("Валюта счёта");
        setSizeFull();
        binder.bind(this, "currency");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

}
