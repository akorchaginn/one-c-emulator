package org.pes.onecemulator.ui.view.fundamentals.fragment.header;

import com.vaadin.ui.VerticalLayout;

public class ViewHeader extends VerticalLayout {

    public ViewHeader(String viewCaption) {
        HeaderTitle applicationTitle = new HeaderTitle(viewCaption);
        addComponent(applicationTitle);
        setMargin(false);
    }
}
