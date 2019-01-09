package org.pes.onecemulator.view.accountingentryadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

class AccountingEntrySumEditField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final String origin;

    AccountingEntrySumEditField(final String origin) {
        this.origin = origin;
        setCaption("Сумма");
        setValue(origin);
        setSizeFull();
        binder.bind(this, "sum");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
