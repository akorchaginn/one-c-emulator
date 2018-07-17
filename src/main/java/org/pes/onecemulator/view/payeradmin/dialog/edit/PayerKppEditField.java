package org.pes.onecemulator.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

class PayerKppEditField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final String origin;

    PayerKppEditField(final String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("КПП");
        setSizeFull();
        binder.bind(this, "kpp");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
