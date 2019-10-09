package org.pes.onecemulator.ui.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerCustomerEditField extends CheckBox {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final boolean origin;

    PayerCustomerEditField(final boolean origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Плательщик");
        setSizeFull();
        binder.bind(this, "customer");
    }

    boolean hasChanges() {
        final boolean now = getValue();
        return origin != now;
    }
}
