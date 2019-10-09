package org.pes.onecemulator.ui.view.payeradmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBox;
import org.pes.onecemulator.model.internal.PayerModel;

class PayerSubcontractorInputField extends CheckBox {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerSubcontractorInputField() {
        setCaption("Субподрядчик");
        binder.bind(this, "subcontractor");
    }
}
