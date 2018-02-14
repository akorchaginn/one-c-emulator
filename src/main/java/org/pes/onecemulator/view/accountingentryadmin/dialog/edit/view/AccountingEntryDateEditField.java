package org.pes.onecemulator.view.accountingentryadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.AccountingEntryModel;

import java.time.LocalDate;

public class AccountingEntryDateEditField extends DateField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final LocalDate origin;

    AccountingEntryDateEditField(LocalDate origin) {
        this.origin = origin;
        setCaption("Дата");
        setValue(origin);
        setSizeFull();
        binder.bind(this, "date");
    }

    boolean hasChanges() {
        LocalDate now = getValue();
        return !origin.equals(now);
    }
}
