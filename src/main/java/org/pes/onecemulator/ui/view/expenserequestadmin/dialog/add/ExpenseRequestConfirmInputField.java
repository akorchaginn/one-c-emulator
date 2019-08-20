package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestConfirmInputField extends CheckBox {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestConfirmInputField() {
        setCaption("Подтверждено");
        setSizeFull();
        binder.bind(this, "confirm");
    }
}
