package org.pes.onecemulator.view.accountingentryadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.AccountingEntryModel;

import java.util.List;

public class AccountingEntryExpenseNumberInputField extends ComboBox<String> {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntryExpenseNumberInputField(List<String> expenseRequests) {
        setCaption("Номер заявки на расход");
        setItems(expenseRequests);
        setSizeFull();
        binder.bind(this, "expenseNumber");
    }
}
