package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoiceExternalIdEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceExternalIdEditField(final String externalId) {
        this.origin = externalId;
        setValue(origin);
        setCaption("Внешний идентификатор");
        setSizeFull();
        binder.bind(this, "externalId");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
