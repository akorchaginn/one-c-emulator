package org.pes.onecemulator.view.expenserequestadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.ExpenseRequestModel;

public class ExpenseRequestSumInputField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestSumInputField() {
        setCaption("Сумма");
        setSizeFull();
        binder.bind(this, "sum");
    }
}
