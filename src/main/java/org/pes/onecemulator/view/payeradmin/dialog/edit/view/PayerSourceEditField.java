package org.pes.onecemulator.view.payeradmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBoxGroup;
import org.pes.onecemulator.model.PayerModel;

import java.util.List;

public class PayerSourceEditField extends CheckBoxGroup<String> {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final List<String> origin;

    private final List<String> old;

    PayerSourceEditField(List<String> old, List<String> origin) {
        super("БД 1С");
        this.origin = origin;
        this.old = old;
        setItems(origin);
        //setItemCaptionGenerator(SourceModel::getName);
        select(old.toArray(new String[old.size()]));
        setSizeFull();
        binder.bind(this, "sources");
    }

    boolean hasChanges() {
        return !getValue().equals(old);
    }
}
