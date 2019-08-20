package org.pes.onecemulator.ui.view.invoiceadmin.dialog.edit;

import com.vaadin.ui.TextField;

import java.util.UUID;

class InvoiceIdReadOnlyField extends TextField {

    InvoiceIdReadOnlyField(final UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
