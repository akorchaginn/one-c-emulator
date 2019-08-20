package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.time.LocalDate;

class AccountingEntryDateEditField extends DateField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final LocalDate origin;

    AccountingEntryDateEditField(final LocalDate origin) {
        this.origin = origin;
        setCaption("Дата");
        setValue(origin);
        setSizeFull();
        binder.bind(this, "date");
    }

    boolean hasChanges() {
        final LocalDate now = getValue();
        return !origin.equals(now);
    }
}
