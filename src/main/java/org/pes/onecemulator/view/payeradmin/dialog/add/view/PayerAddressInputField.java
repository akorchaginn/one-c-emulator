package org.pes.onecemulator.view.payeradmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

public class PayerAddressInputField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerAddressInputField() {
        setCaption("Адрес");
        setSizeFull();
        binder.bind(this, "address");
    }
}
