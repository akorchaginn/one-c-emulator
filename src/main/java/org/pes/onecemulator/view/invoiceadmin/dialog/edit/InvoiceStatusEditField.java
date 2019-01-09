package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoiceStatusEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceStatusEditField(final String status) {
        this.origin = status;
        setValue(origin);
        setCaption("Статус");
        setSizeFull();
        binder.bind(this, "status");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
