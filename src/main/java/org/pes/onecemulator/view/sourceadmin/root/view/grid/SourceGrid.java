package org.pes.onecemulator.view.sourceadmin.root.view.grid;

import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import org.pes.onecemulator.model.SourceModel;

import java.util.ArrayList;
import java.util.List;

public class SourceGrid extends Grid<SourceModel> {

    private static final String ID = "InvoiceGrid";

    private static final ValueProvider<SourceModel, String> payerNameValueProvider = SourceModel::getName;

    private ListDataProvider<SourceModel> dataProvider;

    public SourceGrid() {
        setId(ID);
        setSizeFull();
        setSelectionMode(SelectionMode.MULTI);

        addColumn(payerNameValueProvider).setCaption("Название");
    }

    public void binding(List<SourceModel> sourceModelList) {
        this.dataProvider = DataProvider.ofCollection(sourceModelList);
        setDataProvider(dataProvider);
    }

    public SourceModel selection() {
        return getSelectedItems().stream()
                .findFirst().orElseThrow(IllegalStateException::new);
    }

    public List<SourceModel> allSelections() {
        return new ArrayList<>(getSelectedItems());
    }

    public void filterBy(String searchText) {
        dataProvider.setFilter(sourceModel -> sourceModel.containsWithIgnoreCase(searchText));
    }
}
