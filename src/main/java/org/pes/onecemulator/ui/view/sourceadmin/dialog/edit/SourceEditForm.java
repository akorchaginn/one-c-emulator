package org.pes.onecemulator.ui.view.sourceadmin.dialog.edit;

import com.vaadin.server.CompositeErrorMessage;
import com.vaadin.ui.FormLayout;
import org.pes.onecemulator.model.internal.SourceModel;

class SourceEditForm extends FormLayout {

    private final SourceIdReadOnlyField sourceId;

    private final SourceNameEditField sourceName;

    SourceEditForm(SourceModel target) {
        this.sourceId = new SourceIdReadOnlyField(target.getId());
        this.sourceName = new SourceNameEditField(target.getName());

        addComponents(sourceId, sourceName);
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

    boolean hasChanges() {
        return sourceName.hasChanges();
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
        object.setId(sourceId.valueAsUUID());
        object.setName(sourceName.getValue());

        return object;
    }
}
