package org.pes.onecemulator.view.invoiceadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoiceNumberEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceNumberEditField(String number) {
        this.origin = number;
        setValue(origin);
        setCaption("Номер");
        setSizeFull();
        binder.bind(this, "number");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
