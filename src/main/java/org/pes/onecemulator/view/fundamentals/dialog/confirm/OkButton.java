package org.pes.onecemulator.view.fundamentals.dialog.confirm;

import com.vaadin.ui.Button;

public class OkButton extends Button {

    private static final String ID = "OkButton";

    OkButton() {
        setId(ID);
        setCaption("ОК");
        setWidth(120, Unit.PIXELS);
    }
}
