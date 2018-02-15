package org.pes.onecemulator.view.invoiceadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public class InvoicePayerEditField extends ComboBox<String> {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoicePayerEditField(String payer) {
        this.origin = payer;
        setEmptySelectionAllowed(false);
        setValue(origin);
        setCaption("Плательщик");
        setPlaceholder("Начните вводить значение...");
        setSizeFull();
        setReadOnly(true);
        binder.bind(this, "payerCode");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
