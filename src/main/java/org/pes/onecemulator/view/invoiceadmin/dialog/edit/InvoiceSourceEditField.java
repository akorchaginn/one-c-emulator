package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.util.List;

class InvoiceSourceEditField extends ComboBox<String> {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceSourceEditField(final String source, final List<String> sources) {
        this.origin = source;
        setEmptySelectionAllowed(false);
        setItems(sources);
        setValue(origin);
        setCaption("БД 1С");
        setPlaceholder("Начните вводить значение...");
        setSizeFull();
        binder.bind(this, "source");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
