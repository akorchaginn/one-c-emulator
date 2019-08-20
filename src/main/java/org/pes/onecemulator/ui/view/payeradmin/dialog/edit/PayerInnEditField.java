package org.pes.onecemulator.ui.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerInnEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerInnEditField(final String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("ИНН");
        setSizeFull();
        binder.bind(this, "inn");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
