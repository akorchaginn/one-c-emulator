package org.pes.onecemulator.ui.menu;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import org.pes.onecemulator.ui.view.payeradmin.root.view.PayerAdminView;

class ToPayerAdminViewButton extends Button {

    private final static String ID = "ToPayerAdminViewButton";

    ToPayerAdminViewButton() {
        setId(ID);
        setCaption(PayerAdminView.CAPTION);
        setPrimaryStyleName(ValoTheme.MENU_ITEM);
        addClickListener(event -> getUI().getNavigator().navigateTo(PayerAdminView.VIEW_NAME));
    }

    void selected() {
        addStyleName("selected");
    }

    void unselected() {
        removeStyleName("selected");
    }
}
