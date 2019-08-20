package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

import java.util.List;

class ExpenseRequestSourceInputField extends ComboBox<String> {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestSourceInputField(List<String> sources) {
        setCaption("БД 1С");
        setEmptySelectionAllowed(false);
        setItems(sources);
        setPlaceholder("Начните вводить значение...");
        setSizeFull();
        binder.bind(this, "source");
    }
}
