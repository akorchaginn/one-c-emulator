package org.pes.onecemulator.ui.view.sourceadmin.dialog.edit;

import com.vaadin.ui.TextField;

import java.util.UUID;

class SourceIdReadOnlyField extends TextField {

    SourceIdReadOnlyField(final UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
