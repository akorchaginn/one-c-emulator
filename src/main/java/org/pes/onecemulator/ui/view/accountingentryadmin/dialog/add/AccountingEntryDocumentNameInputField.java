package org.pes.onecemulator.ui.view.accountingentryadmin.dialog.add;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.ui.TextField;
import org.pes.onecemulator.model.internal.AccountingEntryModel;

class AccountingEntryDocumentNameInputField extends TextField {

    final BeanValidationBinder<AccountingEntryModel> binder = new BeanValidationBinder<>(AccountingEntryModel.class);

    AccountingEntryDocumentNameInputField() {
        setCaption("Наименование документа");
        setSizeFull();
        binder.bind(this, "documentName");
    }
}
