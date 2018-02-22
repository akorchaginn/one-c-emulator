package org.pes.onecemulator.view.accountingentryadmin.root.view.grid;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import org.pes.onecemulator.model.AccountingEntryModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountingEntryGrid extends Grid<AccountingEntryModel> {

    private static final String ID = "AccountingEntryGrid";

    private static final ValueProvider<AccountingEntryModel, String> accountingEntryCodeValueProvider =
            AccountingEntryModel::getCode;

    private static final ValueProvider<AccountingEntryModel, LocalDate> accountingEntryDateValueProvider =
            AccountingEntryModel::getDate;

    private static final ValueProvider<AccountingEntryModel, String> accountingEntryDocumentNameValueProvider =
            AccountingEntryModel::getDocumentName;

    private static final ValueProvider<AccountingEntryModel, String> accountingEntryExpenseNumberValueProvider =
            AccountingEntryModel::getExpenseNumber;

    private static final ValueProvider<AccountingEntryModel, String> accountingEntrySumValueProvider =
            AccountingEntryModel::getSum;

    private ListDataProvider<AccountingEntryModel> dataProvider;

    public AccountingEntryGrid() {
        setId(ID);
        setSizeFull();
        setSelectionMode(SelectionMode.MULTI);

        addColumn(accountingEntryCodeValueProvider).setCaption("Код");
        addColumn(accountingEntryDateValueProvider).setCaption("Дата");
        addColumn(accountingEntryDocumentNameValueProvider).setCaption("Наименование документа");
        addColumn(accountingEntryExpenseNumberValueProvider).setCaption("Номер заявки на расход");
        addColumn(accountingEntrySumValueProvider).setCaption("Сумма");
    }

    public void binding(List<AccountingEntryModel> expenseRequestModelList) {
        this.dataProvider = DataProvider.ofCollection(expenseRequestModelList);
        setDataProvider(dataProvider);
    }

    public AccountingEntryModel selection() {
        return getSelectedItems().stream()
                .findFirst().orElseThrow(IllegalStateException::new);
    }

    public List<AccountingEntryModel> allSelections() {
        return new ArrayList<>(getSelectedItems());
    }

    public void filterBy(String searchText) {
        dataProvider.setFilter(accountingEntryModel -> accountingEntryModel.containsWithIgnoreCase(searchText));
    }
}
