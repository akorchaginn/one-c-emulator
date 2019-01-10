package org.pes.onecemulator.view.payeradmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerFullNameInputField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerFullNameInputField() {
        setCaption("Полное название");
        setSizeFull();
        binder.bind(this, "fullName");
    }
}
