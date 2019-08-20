package org.pes.onecemulator.ui.view.fundamentals.dialog.confirm;

import com.vaadin.ui.Button;

class OkButton extends Button {

    private static final String ID = "OkButton";

    OkButton() {
        setId(ID);
        setCaption("ОК");
        setWidth(120, Unit.PIXELS);
    }
}
