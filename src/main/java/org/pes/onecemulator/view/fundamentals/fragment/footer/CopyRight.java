package org.pes.onecemulator.view.fundamentals.fragment.footer;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class CopyRight extends Label {

    CopyRight() {
        setValue("©1C-emulator");
        addStyleName(ValoTheme.LABEL_TINY);
    }
}
