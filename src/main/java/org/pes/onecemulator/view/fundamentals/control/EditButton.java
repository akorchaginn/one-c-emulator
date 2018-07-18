package org.pes.onecemulator.view.fundamentals.control;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

class EditButton extends Button {

    private static final String ID = "EditButton";

    EditButton() {
        setId(ID);
        setIcon(VaadinIcons.PENCIL, "Редактировать");
        addStyleName(ValoTheme.BUTTON_ICON_ONLY);
    }
}
