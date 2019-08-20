package org.pes.onecemulator.ui.view.invoiceadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.util.List;

class InvoiceSourceInputField extends ComboBox<String> {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoiceSourceInputField(List<String> sources) {
        setCaption("БД 1С");
        setEmptySelectionAllowed(false);
        setItems(sources);
        setPlaceholder("Начните вводить значение...");
        setSizeFull();
        binder.bind(this, "source");
    }
}
