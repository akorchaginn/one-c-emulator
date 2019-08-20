package org.pes.onecemulator.ui.view.payeradmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.CheckBoxGroup;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PayerSourceEditField extends CheckBoxGroup<String> {

    final BeanValidationBinder<PayerModel> binder = new BeanValidationBinder<>(PayerModel.class);

    private final List<String> old;

    PayerSourceEditField(final List<String> old, final List<String> origin) {
        super("БД 1С");
        this.old = old;
        setItems(origin);
        select(old.toArray(new String[0]));
        setSizeFull();
        binder.bind(this, "sources");
    }

    boolean hasChanges() {
        return !equalLists(new ArrayList<>(getValue()), old);
    }

    private boolean equalLists(List<String> one, List<String> two) {
        one = new ArrayList<>(one);
        two = new ArrayList<>(two);
        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }
}
