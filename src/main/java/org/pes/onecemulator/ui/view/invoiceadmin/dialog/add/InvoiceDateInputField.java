package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.time.LocalDate;

class InvoiceDateInputField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoiceDateInputField() {
        setCaption("Дата");
        setValue(LocalDate.now());
        setSizeFull();
        binder.bind(this, "date");
    }
}
