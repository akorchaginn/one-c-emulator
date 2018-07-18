package org.pes.onecemulator.ui;

import com.vaadin.ui.HorizontalLayout;
import org.pes.onecemulator.ui.menu.SideMenu;
import org.pes.onecemulator.ui.panel.ViewDisplayPanel;

class ApplicationUIContent extends HorizontalLayout {

    private SideMenu sideMenu = new SideMenu();

    private ViewDisplayPanel viewDisplay = new ViewDisplayPanel();

    public SideMenu getSideMenu() {
        return sideMenu;
    }

    public void setSideMenu(SideMenu sideMenu) {
        this.sideMenu = sideMenu;
    }

    public ViewDisplayPanel getViewDisplay() {
        return viewDisplay;
    }

    public void setViewDisplay(ViewDisplayPanel viewDisplay) {
        this.viewDisplay = viewDisplay;
    }

    ApplicationUIContent() {
        addComponents(sideMenu, viewDisplay);
        setSizeFull();
        setExpandRatio(viewDisplay, 1.0f);
    }
}
