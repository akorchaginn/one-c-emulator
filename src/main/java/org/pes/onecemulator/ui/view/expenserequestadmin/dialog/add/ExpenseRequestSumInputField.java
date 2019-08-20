package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestSumInputField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestSumInputField() {
        setCaption("Сумма");
        setSizeFull();
        binder.bind(this, "sum");
    }
}
