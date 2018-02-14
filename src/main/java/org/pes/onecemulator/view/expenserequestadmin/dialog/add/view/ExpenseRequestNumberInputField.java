package org.pes.onecemulator.view.expenserequestadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.ExpenseRequestModel;

public class ExpenseRequestNumberInputField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestNumberInputField() {
        setCaption("Номер");
        setSizeFull();
        binder.bind(this, "number");
    }
}
