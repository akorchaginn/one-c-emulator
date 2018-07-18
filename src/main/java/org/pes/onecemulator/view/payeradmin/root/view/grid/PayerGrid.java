package org.pes.onecemulator.view.payeradmin.root.view.grid;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import org.pes.onecemulator.model.PayerModel;

import java.util.ArrayList;
import java.util.List;

public class PayerGrid extends Grid<PayerModel> {

    private static final String ID = "PayerGrid";

    private static final ValueProvider<PayerModel, String> payerCodeValueProvider = PayerModel::getCode;

    private static final ValueProvider<PayerModel, String> payerNameValueProvider = PayerModel::getName;

    private static final ValueProvider<PayerModel, String> payerFullNameValueProvider = PayerModel::getFullName;

    private static final ValueProvider<PayerModel, String> payerInnValueProvider = PayerModel::getInn;

    private static final ValueProvider<PayerModel, String> payerKppValueProvider = PayerModel::getKpp;

    private static final ValueProvider<PayerModel, String> payerAddressValueProvider = PayerModel::getAddress;

    private static final ValueProvider<PayerModel, String> payerSourcesValueProvider = payerModel -> payerModel.getSources().toString();

    private ListDataProvider<PayerModel> dataProvider;

    public PayerGrid() {
        setId(ID);
        setSizeFull();
        setSelectionMode(SelectionMode.MULTI);

        addColumn(payerCodeValueProvider).setCaption("Код");
        addColumn(payerNameValueProvider).setCaption("Название");
        addColumn(payerFullNameValueProvider).setCaption("Полное название");
        addColumn(payerInnValueProvider).setCaption("ИНН");
        addColumn(payerKppValueProvider).setCaption("КПП");
        addColumn(payerAddressValueProvider).setCaption("Адрес");
        addColumn(payerSourcesValueProvider).setCaption("БД 1С");
    }

    public void binding(List<PayerModel> payerModelList) {
        this.dataProvider = DataProvider.ofCollection(payerModelList);
        setDataProvider(dataProvider);
    }

    public PayerModel selection() {
        return getSelectedItems()
                .stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public List<PayerModel> allSelections() {
        return new ArrayList<>(getSelectedItems());
    }

    public void filterBy(final String searchText) {
        dataProvider.setFilter(payerModel -> payerModel.containsWithIgnoreCase(searchText));
    }
}
