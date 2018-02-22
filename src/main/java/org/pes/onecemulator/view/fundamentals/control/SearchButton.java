package org.pes.onecemulator.view.fundamentals.control;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class SearchButton extends Button {

    private static final String ID = "SearchButton";

    SearchButton() {
        setId(ID);
        setIcon(VaadinIcons.SEARCH, "Поиск");
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
    }
}
