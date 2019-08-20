package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.util.List;

class AccountingEntryExpenseNumberInputField extends ComboBox<String> {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntryExpenseNumberInputField(List<String> expenseRequests) {
        setCaption("Номер заявки на расход");
        setEmptySelectionAllowed(false);
        setItems(expenseRequests);
        setPlaceholder("Начните вводить значение...");
        setSizeFull();
        binder.bind(this, "expenseNumber");
    }
}
