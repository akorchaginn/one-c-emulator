package org.pes.onecemulator.view.top.view;

import com.vaadin.ui.VerticalLayout;

class TopViewBody extends VerticalLayout {

    TopViewBody() {
        setSizeFull();
        addComponents(new ApplicationDescription());
    }
}
