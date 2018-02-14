package org.pes.onecemulator.view.payeradmin.dialog.edit.view;

import com.vaadin.ui.TextField;

import java.util.UUID;

public class PayerIdReadOnlyField extends TextField {

    PayerIdReadOnlyField(UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
