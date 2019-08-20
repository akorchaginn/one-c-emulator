package org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoiceSumEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceSumEditField(final String sum) {
        this.origin = sum;
        setValue(origin);
        setCaption("Сумма счёта в валюте счета");
        setSizeFull();
        binder.bind(this, "sum");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }

}
