package org.pes.onecemulator.view.expenserequestadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.ExpenseRequestModel;

public class ExpenseRequestSumEditField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final String origin;

    ExpenseRequestSumEditField(String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Сумма");
        setSizeFull();
        binder.bind(this, "sum");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
