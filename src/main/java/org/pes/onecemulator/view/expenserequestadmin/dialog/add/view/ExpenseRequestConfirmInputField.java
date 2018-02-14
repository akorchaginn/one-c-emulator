package org.pes.onecemulator.view.expenserequestadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.ExpenseRequestModel;

public class ExpenseRequestConfirmInputField extends CheckBox {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestConfirmInputField() {
        setCaption("Подтверждено");
        setSizeFull();
        binder.bind(this, "confirm");
    }
}
