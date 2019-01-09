package org.pes.onecemulator.view.accountingentryadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

class AccountingEntryCodeEditField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final String origin;

    AccountingEntryCodeEditField(final String origin) {
        this.origin = origin;
        setCaption("Код");
        setValue(origin);
        setSizeFull();
        binder.bind(this, "code");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
