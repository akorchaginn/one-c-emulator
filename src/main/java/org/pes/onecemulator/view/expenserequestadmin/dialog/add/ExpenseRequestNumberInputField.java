package org.pes.onecemulator.view.expenserequestadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestNumberInputField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestNumberInputField() {
        setCaption("Номер");
        setSizeFull();
        binder.bind(this, "number");
    }
}
