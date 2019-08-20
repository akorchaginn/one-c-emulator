package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

class ExpenseRequestCurrencyEditField extends TextField {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final String origin;

    ExpenseRequestCurrencyEditField(final String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Валюта");
        setSizeFull();
        binder.bind(this, "currency");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
