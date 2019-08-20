package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.edit;

import com.vaadin.ui.TextField;

import java.util.UUID;

class AccountingEntryIdReadOnlyField extends TextField {

    AccountingEntryIdReadOnlyField(final UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
