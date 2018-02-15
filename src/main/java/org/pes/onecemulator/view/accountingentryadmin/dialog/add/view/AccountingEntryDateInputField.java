package org.pes.onecemulator.view.accountingentryadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.DateField;
import org.pes.onecemulator.model.AccountingEntryModel;

import java.time.LocalDate;

public class AccountingEntryDateInputField extends DateField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntryDateInputField() {
        setCaption("Дата");
        setValue(LocalDate.now());
        setSizeFull();
        binder.bind(this, "date");
    }
}
