package org.pes.onecemulator.view.payeradmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.PayerModel;

public class PayerCodeInputField extends TextField {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerCodeInputField() {
        setCaption("Код");
        setSizeFull();
        binder.bind(this, "code");
    }
}
