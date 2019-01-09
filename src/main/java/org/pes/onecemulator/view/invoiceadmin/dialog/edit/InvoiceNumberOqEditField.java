package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoiceNumberOqEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceNumberOqEditField(final String numberOq) {
        this.origin = numberOq;
        setValue(origin);
        setCaption("Номер в OQ");
        setSizeFull();
        binder.bind(this, "numberOq");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
