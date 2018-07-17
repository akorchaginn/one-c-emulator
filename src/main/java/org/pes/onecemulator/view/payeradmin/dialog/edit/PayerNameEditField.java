package org.pes.onecemulator.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

class PayerNameEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerNameEditField(String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Название");
        setSizeFull();
        binder.bind(this, "name");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
