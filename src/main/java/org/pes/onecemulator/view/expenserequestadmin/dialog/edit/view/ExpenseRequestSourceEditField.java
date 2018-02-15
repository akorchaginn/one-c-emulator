package org.pes.onecemulator.view.expenserequestadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.ComboBox;
import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;

public class ExpenseRequestSourceEditField extends ComboBox<String> {

    final BeanValidationBinder<ExpenseRequestModel> binder = new BeanValidationBinder<>(ExpenseRequestModel.class);

    private final String origin;

    ExpenseRequestSourceEditField(String origin, List<String> sources) {
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
        String now = getValue();
        return !origin.equals(now);
    }
}
