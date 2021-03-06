package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.time.LocalDate;

class AccountingEntryDateInputField extends DateField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntryDateInputField() {
        setCaption("Дата");
        setValue(LocalDate.now());
        setSizeFull();
        binder.bind(this, "date");
    }
}
