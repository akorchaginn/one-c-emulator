package org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.time.LocalDate;

class InvoicePaymentDateEditField extends DateField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final LocalDate origin;

    InvoicePaymentDateEditField(final LocalDate paymentDate) {
        this.origin = paymentDate;
        setValue(origin);
        setCaption("Дата оплаты");
        setSizeFull();
        binder.bind(this, "paymentDate");
    }

    boolean hasChanges() {
        final LocalDate now = getValue();
        return !origin.equals(now);
    }

}
