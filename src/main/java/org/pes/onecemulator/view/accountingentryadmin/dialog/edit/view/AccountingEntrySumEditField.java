package org.pes.onecemulator.view.accountingentryadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.AccountingEntryModel;

public class AccountingEntrySumEditField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final String origin;

    AccountingEntrySumEditField(String origin) {
        this.origin = origin;
        setCaption("Сумма");
        setValue(origin);
        setSizeFull();
        binder.bind(this, "sum");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
