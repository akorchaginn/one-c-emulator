package org.pes.onecemulator.ui.view.fundamentals.dialog.form;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

class SaveButton extends Button {

    private static final String ID = "SaveButton";

    SaveButton() {
        setId(ID);
        setCaption("Сохранить");
        setWidth(120, Unit.PIXELS);
        addStyleNames(ValoTheme.BUTTON_FRIENDLY);
    }
}
