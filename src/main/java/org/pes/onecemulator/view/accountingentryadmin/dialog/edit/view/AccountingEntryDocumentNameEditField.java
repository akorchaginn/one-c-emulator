package org.pes.onecemulator.view.accountingentryadmin.dialog.edit.view;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.AccountingEntryModel;

public class AccountingEntryDocumentNameEditField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    private final String origin;

    AccountingEntryDocumentNameEditField(String origin) {
        this.origin = origin;
        setCaption("Наименование документа");
        setValue(origin);
        setSizeFull();
        binder.bind(this, "documentName");
    }

    boolean hasChanges() {
        String now = getValue();
        return !origin.equals(now);
    }
}
