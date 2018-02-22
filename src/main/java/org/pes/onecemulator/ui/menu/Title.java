package org.pes.onecemulator.ui.menu;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Title extends Label {

    Title() {
        setValue("1C-emulator");
        addStyleName(ValoTheme.MENU_TITLE);
    }
}
