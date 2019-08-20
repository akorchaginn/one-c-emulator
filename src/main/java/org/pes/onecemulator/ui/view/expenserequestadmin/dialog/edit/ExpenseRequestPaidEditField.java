package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestPaidEditField extends CheckBox {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final boolean origin;

    ExpenseRequestPaidEditField(final boolean origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Оплачено");
        setSizeFull();
        binder.bind(this, "paid");
    }

    boolean hasChanges() {
        final boolean now = getValue();
        return origin != now;
    }
}
