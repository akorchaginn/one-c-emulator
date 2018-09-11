package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoiceSumRUBEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceSumRUBEditField(final String sumRUB) {
        this.origin = sumRUB;
        setValue(origin);
        setCaption("Сумма счета в рублях");
        setSizeFull();
        binder.bind(this, "sumRUB");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }

}
