package org.pes.onecemulator.view.expenserequestadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.ExpenseRequestModel;

class ExpenseRequestNumberEditField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final String origin;

    ExpenseRequestNumberEditField(final String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Номер");
        setSizeFull();
        binder.bind(this, "number");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
