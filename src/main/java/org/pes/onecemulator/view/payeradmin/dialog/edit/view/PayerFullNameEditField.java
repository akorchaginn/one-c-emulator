package org.pes.onecemulator.view.payeradmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

public class PayerFullNameEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerFullNameEditField(String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Полное название");
        setSizeFull();
        binder.bind(this, "fullName");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
