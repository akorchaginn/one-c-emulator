package org.pes.onecemulator.ui.menu;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import org.pes.onecemulator.ui.view.expenserequestadmin.root.view.ExpenseRequestAdminView;

class ToExpenseRequestAdminViewButton extends Button {

    private final static String ID = "ToExpenseRequestAdminViewButton";

    ToExpenseRequestAdminViewButton() {
        setId(ID);
        setCaption(ExpenseRequestAdminView.CAPTION);
        setPrimaryStyleName(ValoTheme.MENU_ITEM);
        addClickListener(event -> getUI().getNavigator().navigateTo(ExpenseRequestAdminView.VIEW_NAME));
    }

    void selected() {
        addStyleName("selected");
    }

    void unselected() {
        removeStyleName("selected");
    }
}
