package org.pes.onecemulator.view.expenserequestadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.ExpenseRequestModel;

public class ExpenseRequestPaidInputField extends CheckBox {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestPaidInputField() {
        setCaption("Оплачено");
        setSizeFull();
        binder.bind(this, "paid");
    }
}
