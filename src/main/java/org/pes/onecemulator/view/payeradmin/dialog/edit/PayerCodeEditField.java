package org.pes.onecemulator.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

class PayerCodeEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerCodeEditField(String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Код");
        setSizeFull();
        binder.bind(this, "code");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
