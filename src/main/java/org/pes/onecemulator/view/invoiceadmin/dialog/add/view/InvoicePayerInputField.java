package org.pes.onecemulator.view.invoiceadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoicePayerInputField extends ComboBox<String> {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePayerInputField() {
        setCaption("Плательщик");
        setSizeFull();
        setReadOnly(true);
        binder.bind(this, "payerCode");
    }
}
