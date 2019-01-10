package org.pes.onecemulator.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerFullNameEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerFullNameEditField(final String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Полное название");
        setSizeFull();
        binder.bind(this, "fullName");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
