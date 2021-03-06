package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.internal.InvoiceModel;

class InvoicePayerInputField extends ComboBox<String> {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePayerInputField() {
        setCaption("Плательщик");
        setEmptySelectionAllowed(false);
        setPlaceholder("Начните вводить значение...");
        setSizeFull();
        setReadOnly(true);
        binder.bind(this, "payerCode");
    }
}
