package org.pes.onecemulator.view.expenserequestadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.ExpenseRequestModel;

public class ExpenseRequestPaidEditField extends CheckBox {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final Boolean origin;

    ExpenseRequestPaidEditField(Boolean origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Оплачено");
        setSizeFull();
        binder.bind(this, "paid");
    }

    boolean hasChanges() {
        Boolean now = getValue();
        return !origin.equals(now);
    }
}
