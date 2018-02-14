package org.pes.onecemulator.view.top.view;

import com.vaadin.ui.VerticalLayout;

class TopViewBody extends VerticalLayout {

    private final ApplicationDescription description = new ApplicationDescription();

    TopViewBody() {
        setSizeFull();
        addComponents(description);
    }
}
