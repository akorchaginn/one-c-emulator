package org.pes.onecemulator.view.fundamentals.control;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

class SearchField extends CssLayout {

    final SearchTextField searchText;

    final SearchButton searchButton;

    SearchField(String searchTextPlaceholder) {
        searchText = new SearchTextField(searchTextPlaceholder);
        searchButton = new SearchButton();
        addComponents(searchText, searchButton);
        addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
    }
}
