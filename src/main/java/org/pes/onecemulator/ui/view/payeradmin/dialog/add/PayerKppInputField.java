package org.pes.onecemulator.ui.view.payeradmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerKppInputField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerKppInputField() {
        setCaption("КПП");
        setSizeFull();
        binder.bind(this, "kpp");
    }
}
