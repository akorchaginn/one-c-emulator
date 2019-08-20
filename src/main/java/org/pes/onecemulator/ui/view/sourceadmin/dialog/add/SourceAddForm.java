package org.pes.onecemulator.ui.view.sourceadmin.dialog.add;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.internal.SourceModel;

class SourceAddForm extends FormLayout {

    private final SourceNameInputField sourceName;

    SourceAddForm() {
        this.sourceName = new SourceNameInputField();
        addComponents(sourceName);
        setMargin(false);
    }

    void validate() {
        sourceName.binder.validate();
    }

    boolean hasValidationErrors() {
        return !allFieldsAreValid();
    }

    private boolean allFieldsAreValid() {
        return sourceName.binder.isValid();
    }

    String errorMessagesAsHtml() {
        final CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                sourceName.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    SourceModel valueAsObject() {
        final SourceModel object = new SourceModel();
        object.setName(sourceName.getValue());

        return object;
    }
}
