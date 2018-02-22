package org.pes.onecemulator.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

public class PayerInnEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerInnEditField(String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("ИНН");
        setSizeFull();
        binder.bind(this, "inn");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
