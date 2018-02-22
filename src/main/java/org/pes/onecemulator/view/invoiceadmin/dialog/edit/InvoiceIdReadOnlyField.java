package org.pes.onecemulator.view.invoiceadmin.dialog.edit;

import com.vaadin.ui.TextField;

import java.util.UUID;

public class InvoiceIdReadOnlyField extends TextField {

    InvoiceIdReadOnlyField(UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
