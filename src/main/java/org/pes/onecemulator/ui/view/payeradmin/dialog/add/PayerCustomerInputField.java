package org.pes.onecemulator.ui.view.payeradmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerCustomerInputField extends CheckBox {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerCustomerInputField() {
        setCaption("Плательщик");
        setSizeFull();
        binder.bind(this, "customer");
    }
}
