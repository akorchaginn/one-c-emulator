package org.pes.onecemulator.ui.view.payeradmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerInnInputField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerInnInputField() {
        setCaption("ИНН");
        setSizeFull();
        binder.bind(this, "inn");
    }
}
