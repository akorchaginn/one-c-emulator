package org.pes.onecemulator.ui.menu;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import org.pes.onecemulator.ui.view.sourceadmin.root.view.SourceAdminView;

class ToSourceAdminViewButton extends Button {

    private final static String ID = "ToSourceAdminViewButton";

    ToSourceAdminViewButton() {
        setId(ID);
        setCaption(SourceAdminView.CAPTION);
        setPrimaryStyleName(ValoTheme.MENU_ITEM);
        addClickListener(event -> getUI().getNavigator().navigateTo(SourceAdminView.VIEW_NAME));
    }

    void selected() {
        addStyleName("selected");
    }

    void unselected() {
        removeStyleName("selected");
    }
}
