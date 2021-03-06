package org.pes.onecemulator.ui.view.accountingentryadmin.root.view;

import org.pes.onecemulator.model.internal.AccountingEntryModel;

import java.util.List;

public interface IAccountingEntryAdminView {

    void bindingGridData(List<AccountingEntryModel> accountingEntryModelList);

    void doFilterBySearchText();

    void toStateOfOnlyCanAdd();

    void toStateOfCanAll();

    void toStateOfCanAddAndDelete();

    void launchAccountingEntryAddDialog();

    void launchAccountingEntryEditDialog();

    void launchDeleteAccountingEntryConfirmDialog();

    List<AccountingEntryModel> allGridSelections();

    AccountingEntryModel gridSelection();
}
