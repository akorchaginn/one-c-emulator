package org.pes.onecemulator.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.InvoiceModel;

import java.time.LocalDate;

class InvoicePaymentDateInputField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePaymentDateInputField() {
        setCaption("Дата оплаты");
        setValue(LocalDate.now());
        setSizeFull();
        binder.bind(this, "paymentDate");
    }
}
