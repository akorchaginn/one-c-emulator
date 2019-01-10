package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.time.LocalDate;

class InvoiceDateEditField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final LocalDate origin;

    InvoiceDateEditField(final LocalDate localDate) {
        this.origin = localDate;
        setValue(origin);
        setCaption("Дата");
        setSizeFull();
        binder.bind(this, "date");
    }

    boolean hasChanges() {
        final LocalDate now = getValue();
        return !origin.equals(now);
    }

}
