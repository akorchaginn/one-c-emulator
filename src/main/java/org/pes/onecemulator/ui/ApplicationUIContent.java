package org.pes.onecemulator.ui;

import com.vaadin.ui.HorizontalLayout;
import org.pes.onecemulator.ui.menu.SideMenu;
import org.pes.onecemulator.ui.panel.ViewDisplayPanel;

class ApplicationUIContent extends HorizontalLayout {

    final SideMenu sideMenu = new SideMenu();
    final ViewDisplayPanel viewDisplay = new ViewDisplayPanel();

    ApplicationUIContent() {
        addComponents(sideMenu, viewDisplay);
        setSizeFull();
        setExpandRatio(viewDisplay, 1.0f);
    }
}
