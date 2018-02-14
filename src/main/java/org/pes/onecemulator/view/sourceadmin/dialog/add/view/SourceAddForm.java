package org.pes.onecemulator.view.sourceadmin.dialog.add.view;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.SourceModel;

public class SourceAddForm extends FormLayout {

    private final SourceNameInputField sourceName;

    //Version originalVersion;

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
        // note: getErrorMessage() always return null before binder.validate()
        CompositeErrorMessage compositeErrorMessage = new CompositeErrorMessage(
                sourceName.getErrorMessage());
        return String.format("%s<br/>%s",
                compositeErrorMessage.getErrorLevel().intValue(),
                compositeErrorMessage.getFormattedHtmlMessage());
    }

    SourceModel valueAsObject() {
        SourceModel object = new SourceModel();
        object.setName(sourceName.getValue());

        return object;
    }
}
