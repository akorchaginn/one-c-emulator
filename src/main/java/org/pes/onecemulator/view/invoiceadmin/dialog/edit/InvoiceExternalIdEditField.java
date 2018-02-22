package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoiceExternalIdEditField extends TextField {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    private final String origin;

    InvoiceExternalIdEditField(String externalId) {
        this.origin = externalId;
        setValue(origin);
        setCaption("Внешний идентификатор");
        setSizeFull();
        binder.bind(this, "externalId");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
