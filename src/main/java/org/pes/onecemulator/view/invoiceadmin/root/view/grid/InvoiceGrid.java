package org.pes.onecemulator.view.invoiceadmin.root.view.grid;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import org.pes.onecemulator.model.internal.InvoiceModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceGrid extends Grid<InvoiceModel> {

    private static final String ID = "InvoiceGrid";

    private static final ValueProvider<InvoiceModel, String> invoiceSourceValueProvider = InvoiceModel::getSource;

    private static final ValueProvider<InvoiceModel, LocalDate> invoiceDateValueProvider = InvoiceModel::getDate;

    private static final ValueProvider<InvoiceModel, String> invoiceNumberValueProvider = InvoiceModel::getNumber;

    private static final ValueProvider<InvoiceModel, String> invoiceNumberOqValueProvider = InvoiceModel::getNumberOq;

    private static final ValueProvider<InvoiceModel, String> invoicePayerValueProvider = InvoiceModel::getPayerCode;

    private static final ValueProvider<InvoiceModel, LocalDate> invoicePaymentDateValueProvider = InvoiceModel::getPaymentDate;

    private static final ValueProvider<InvoiceModel, String> invoicePaymentSumValueProvider = InvoiceModel::getPaymentSum;

    private static final ValueProvider<InvoiceModel, String> invoiceStatusValueProvider = InvoiceModel::getStatus;

    private static final ValueProvider<InvoiceModel, String> invoiceSumValueProvider = InvoiceModel::getSum;

    private static final ValueProvider<InvoiceModel, String> invoiceExternalIdValueProvider = InvoiceModel::getExternalId;

    private ListDataProvider<InvoiceModel> dataProvider;

    public InvoiceGrid() {
        setId(ID);
        setSizeFull();
        setSelectionMode(SelectionMode.MULTI);

        addColumn(invoiceSourceValueProvider).setCaption("БД 1С");
        addColumn(invoiceDateValueProvider).setCaption("Дата");
        addColumn(invoiceNumberValueProvider).setCaption("Номер");
        addColumn(invoiceNumberOqValueProvider).setCaption("Номер из OQ");
        addColumn(invoicePayerValueProvider).setCaption("Плательщик");
        addColumn(invoicePaymentDateValueProvider).setCaption("Дата оплаты");
        addColumn(invoicePaymentSumValueProvider).setCaption("Сумма оплаты в валюте платежа");
        addColumn(invoiceStatusValueProvider).setCaption("Статус");
        addColumn(invoiceSumValueProvider).setCaption("Сумма счета в валюте счета");
        addColumn(invoiceExternalIdValueProvider).setCaption("Внешний идентификатор");
    }

    public void binding(List<InvoiceModel> invoiceModelList) {
        this.dataProvider = DataProvider.ofCollection(invoiceModelList);
        setDataProvider(dataProvider);
    }

    public InvoiceModel selection() {
        return getSelectedItems().stream()
                .findFirst().orElseThrow(IllegalStateException::new);
    }

    public List<InvoiceModel> allSelections() {
        return new ArrayList<>(getSelectedItems());
    }

    public void filterBy(final String searchText) {
        dataProvider.setFilter(invoiceModel -> invoiceModel.containsWithIgnoreCase(searchText));
    }
}
