package org.pes.onecemulator.view.accountingentryadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

class AccountingEntrySumInputField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntrySumInputField() {
        setCaption("Сумма");
        setSizeFull();
        binder.bind(this, "sum");
    }
}
