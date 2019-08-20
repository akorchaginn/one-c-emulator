package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestPaidInputField extends CheckBox {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestPaidInputField() {
        setCaption("Оплачено");
        setSizeFull();
        binder.bind(this, "paid");
    }
}
