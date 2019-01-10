package org.pes.onecemulator.view.expenserequestadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestCurrencyInputField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    ExpenseRequestCurrencyInputField() {
        setCaption("Валюта");
        setSizeFull();
        binder.bind(this, "currency");
    }
}
