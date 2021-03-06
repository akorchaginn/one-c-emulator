package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.edit;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

class AccountingEntryDocumentNameEditField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final String origin;

    AccountingEntryDocumentNameEditField(final String origin) {
        this.origin = origin;
        setCaption("Наименование документа");
        setValue(origin);
        setSizeFull();
        binder.bind(this, "documentName");
    }

    boolean hasChanges() {
        final String now = getValue();
        return !origin.equals(now);
    }
}
