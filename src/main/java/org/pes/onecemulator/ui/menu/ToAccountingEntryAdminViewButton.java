package org.pes.onecemulator.ui.menu;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import org.pes.onecemulator.ui.view.accountingentryadmin.root.view.AccountingEntryAdminView;

class ToAccountingEntryAdminViewButton extends Button {

    private final static String ID = "ToAccountingEntryAdminViewButton";

    ToAccountingEntryAdminViewButton() {
        setId(ID);
        setCaption(AccountingEntryAdminView.CAPTION);
        setPrimaryStyleName(ValoTheme.MENU_ITEM);
        addClickListener(event -> getUI().getNavigator().navigateTo(AccountingEntryAdminView.VIEW_NAME));
    }

    void selected() {
        addStyleName("selected");
    }

    void unselected() {
        removeStyleName("selected");
    }
}
