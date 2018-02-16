package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.InvoiceModel;

import java.time.LocalDate;

public class InvoiceDateInputField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoiceDateInputField() {
        setCaption("Дата");
        setValue(LocalDate.now());
        setSizeFull();
        binder.bind(this, "date");
    }
}
