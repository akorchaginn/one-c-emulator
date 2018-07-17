package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoiceSumEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceSumEditField(String sum) {
        this.origin = sum;
        setValue(origin);
        setCaption("Сумма счёта");
        setSizeFull();
        binder.bind(this, "invoiceSum");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

}
