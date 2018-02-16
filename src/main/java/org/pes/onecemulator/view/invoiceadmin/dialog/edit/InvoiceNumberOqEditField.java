package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoiceNumberOqEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceNumberOqEditField(String numberOq) {
        this.origin = numberOq;
        setValue(origin);
        setCaption("Номер в OQ");
        setSizeFull();
        binder.bind(this, "numberOq");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
