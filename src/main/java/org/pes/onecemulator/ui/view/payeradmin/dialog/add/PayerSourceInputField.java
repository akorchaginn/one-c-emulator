package org.pes.onecemulator.ui.view.payeradmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBoxGroup;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.List;

class PayerSourceInputField extends CheckBoxGroup<String> {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    PayerSourceInputField(List<String> origin) {
        super("БД 1С");
        setItems(origin);
        setSizeFull();
        binder.bind(this, "sources");
    }
}
