package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoiceNumberInputField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoiceNumberInputField() {
        setCaption("Номер");
        setSizeFull();
        binder.bind(this, "number");
    }
}
