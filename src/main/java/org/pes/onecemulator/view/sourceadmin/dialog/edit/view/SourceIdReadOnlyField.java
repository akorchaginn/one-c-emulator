package org.pes.onecemulator.view.sourceadmin.dialog.edit.view;

import com.vaadin.ui.TextField;

import java.util.UUID;

public class SourceIdReadOnlyField extends TextField {

    SourceIdReadOnlyField(UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
