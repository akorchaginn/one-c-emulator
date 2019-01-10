package org.pes.onecemulator.view.expenserequestadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestConfirmEditField extends CheckBox {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final boolean origin;

    ExpenseRequestConfirmEditField(final boolean origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Подтверждено");
        setSizeFull();
        binder.bind(this, "confirm");
    }

    boolean hasChanges() {
        final boolean now = Boolean.TRUE.equals(getValue());
        return origin != now;
    }
}
