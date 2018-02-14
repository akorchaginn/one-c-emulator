package org.pes.onecemulator.view.invoiceadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.InvoiceModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InvoiceDateEditField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final LocalDate origin;

    InvoiceDateEditField(LocalDate localDate) {
        this.origin = localDate;
        setValue(origin);
        setCaption("Дата");
        setSizeFull();
        binder.bind(this, "date");
    }

    boolean hasChanges() {
        LocalDate now = getValue();
        return !origin.equals(now);
    }

    Calendar valueAsCalendar() {
        return GregorianCalendar.from(getValue().atStartOfDay(ZoneId.systemDefault()));
    }
}
