package org.pes.onecemulator.view.accountingentryadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.AccountingEntryModel;

public class AccountingEntrySumInputField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntrySumInputField() {
        setCaption("Сумма");
        setSizeFull();
        binder.bind(this, "sum");
    }
}
