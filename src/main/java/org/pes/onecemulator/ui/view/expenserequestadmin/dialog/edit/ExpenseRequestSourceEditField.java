package org.pes.onecemulator.ui.view.expenserequestadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

import java.util.List;

class ExpenseRequestSourceEditField extends ComboBox<String> {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final String origin;

    ExpenseRequestSourceEditField(final String origin, final List<String> sources) {
        this.origin = origin;
        setEmptySelectionAllowed(false);
        setItems(sources);
        setValue(origin);
        setPlaceholder("Начните вводить значение...");
        setCaption("БД 1С");
        setSizeFull();
        binder.bind(this, "source");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
