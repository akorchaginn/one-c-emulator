package org.pes.onecemulator.view.payeradmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

public class PayerAddressEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerAddressEditField(String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Адрес");
        setSizeFull();
        binder.bind(this, "address");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
