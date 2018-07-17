package org.pes.onecemulator.view.fundamentals.layout;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.view.fundamentals.fragment.footer.ViewFooter;
import org.pes.onecemulator.view.fundamentals.fragment.header.ViewHeader;

public abstract class BaseViewLayout extends VerticalLayout {

    public BaseViewLayout() {
        ViewFooter viewFooter = new ViewFooter();
        addComponent(viewFooter);
        setSizeFull();
    }

    protected void addHeaderAndBody(ViewHeader viewHeader, Component body) {
        addComponentAsFirst(viewHeader);
        addComponent(body, 1);
        setExpandRatio(body, 1.0f);
    }
}
