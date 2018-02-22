package org.pes.onecemulator.view.fundamentals.fragment.header;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class HeaderTitle extends Label {

    HeaderTitle(String value) {
        setValue(value);
        addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_NO_MARGIN);
    }
}
