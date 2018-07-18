package org.pes.onecemulator.view.expenserequestadmin.root.view.grid;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.ArrayList;
import java.util.List;

public class ExpenseRequestGrid extends Grid<ExpenseRequestModel> {

    private static final String ID = "ExpenseRequestGrid";

    private static final ValueProvider<ExpenseRequestModel, String> expenseRequestSourceValueProvider =
            ExpenseRequestModel::getSource;

    private static final ValueProvider<ExpenseRequestModel, String> expenseRequestCurrencyValueProvider =
            ExpenseRequestModel::getCurrency;

    private static final ValueProvider<ExpenseRequestModel, Boolean> expenseRequestConfirmValueProvider =
            ExpenseRequestModel::getConfirm;

    private static final ValueProvider<ExpenseRequestModel, Boolean> expenseRequestPaidValueProvider =
            ExpenseRequestModel::getPaid;

    private static final ValueProvider<ExpenseRequestModel, String> expenseRequestNumberValueProvider =
            ExpenseRequestModel::getNumber;

    private static final ValueProvider<ExpenseRequestModel, String> expenseRequestSumValueProvider =
            ExpenseRequestModel::getSum;

    private ListDataProvider<ExpenseRequestModel> dataProvider;

    public ExpenseRequestGrid() {
        setId(ID);
        setSizeFull();
        setSelectionMode(SelectionMode.MULTI);

        addColumn(expenseRequestSourceValueProvider).setCaption("БД 1С");
        addColumn(expenseRequestCurrencyValueProvider).setCaption("Валюта");
        addColumn(expenseRequestConfirmValueProvider).setCaption("Подтвержден");
        addColumn(expenseRequestPaidValueProvider).setCaption("Оплачен");
        addColumn(expenseRequestNumberValueProvider).setCaption("Номер");
        addColumn(expenseRequestSumValueProvider).setCaption("Сумма");
    }

    public void binding(List<ExpenseRequestModel> expenseRequestModelList) {
        this.dataProvider = DataProvider.ofCollection(expenseRequestModelList);
        setDataProvider(dataProvider);
    }

    public ExpenseRequestModel selection() {
        return getSelectedItems()
                .stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public List<ExpenseRequestModel> allSelections() {
        return new ArrayList<>(getSelectedItems());
    }

    public void filterBy(final String searchText) {
        dataProvider.setFilter(sourceModel -> sourceModel.containsWithIgnoreCase(searchText));
    }
}
