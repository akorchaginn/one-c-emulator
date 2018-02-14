package org.pes.onecemulator.view.payeradmin.root.view.control;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class AddButton extends Button {

    private static final String ID = "AddButton";

    AddButton() {
        setId(ID);
        setIcon(VaadinIcons.PLUS, "Добавить");
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
    }
}
