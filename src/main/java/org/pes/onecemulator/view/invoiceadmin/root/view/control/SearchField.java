package org.pes.onecemulator.view.invoiceadmin.root.view.control;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SearchField extends CssLayout {

    final SearchTextField searchText = new SearchTextField();

    final SearchButton searchButton = new SearchButton();

    SearchField() {
        addComponents(searchText, searchButton);
        addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
    }
}
