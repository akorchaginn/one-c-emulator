package org.pes.onecemulator.view.fundamentals.fragment.footer;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

class CopyRight extends Label {

    CopyRight() {
        setValue("©1C-emulator");
        addStyleName(ValoTheme.LABEL_TINY);
    }
}
