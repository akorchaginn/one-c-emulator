package org.pes.onecemulator.view.invoiceadmin.dialog.add.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.InvoiceModel;

import java.util.List;

public class InvoicePayerInputField extends ComboBox<String> {

    final BeanValidationBinder<InvoiceModel> binder = new BeanValidationBinder<>(InvoiceModel.class);

    InvoicePayerInputField(List<String> payers) {
        setCaption("Плательщик");
        setItems(payers);
        setSizeFull();
        binder.bind(this, "payerCode");
    }
}
