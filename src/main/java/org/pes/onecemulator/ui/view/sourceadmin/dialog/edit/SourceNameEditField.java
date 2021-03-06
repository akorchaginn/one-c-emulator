package org.pes.onecemulator.ui.view.sourceadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.SourceModel;

class SourceNameEditField extends TextField {

    final BeanValidationBinder<SourceModel> binder = new BeanValidationBinder<>(SourceModel.class);

    private final String origin;

    SourceNameEditField(final String origin) {
        this.origin = origin;
        setValue(origin);
        setCaption("Название");
        setSizeFull();
        binder.bind(this, "name");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
