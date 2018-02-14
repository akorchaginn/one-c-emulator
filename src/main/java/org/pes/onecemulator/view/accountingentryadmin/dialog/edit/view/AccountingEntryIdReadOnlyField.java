package org.pes.onecemulator.view.accountingentryadmin.dialog.edit.view;

import com.vaadin.ui.TextField;

import java.util.UUID;

public class AccountingEntryIdReadOnlyField extends TextField {

    AccountingEntryIdReadOnlyField(UUID origin) {
        setValue(origin.toString());
        setCaption("Id");
        setSizeFull();
        setReadOnly(true);
    }

    UUID valueAsUUID() {
        return UUID.fromString(getValue());
    }
}
