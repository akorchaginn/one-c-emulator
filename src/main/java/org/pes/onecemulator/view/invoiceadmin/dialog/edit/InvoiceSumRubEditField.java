package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

class InvoiceSumRubEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceSumRubEditField(String sum) {
        this.origin = sum;
        setValue(origin);
        setCaption("Сумма в рублях");
        setSizeFull();
        binder.bind(this, "invoiceSumRUB");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }

}
