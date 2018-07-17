package org.pes.onecemulator.view.sourceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.SourceModel;

class SourceNameEditField extends TextField {

    final BeanValidationBinder<SourceModel> binder = new BeanValidationBinder<>(SourceModel.class);

    private final String origin;

    SourceNameEditField(String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Название");
        setSizeFull();
        binder.bind(this, "name");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
