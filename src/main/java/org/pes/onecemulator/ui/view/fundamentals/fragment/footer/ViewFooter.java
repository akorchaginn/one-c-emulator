package org.pes.onecemulator.ui.view.fundamentals.fragment.footer;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class ViewFooter extends VerticalLayout {

    public ViewFooter() {
        CopyRight copyRight = new CopyRight();
        addComponent(copyRight);
        setMargin(false);
        setComponentAlignment(copyRight, Alignment.MIDDLE_CENTER);
    }
}
