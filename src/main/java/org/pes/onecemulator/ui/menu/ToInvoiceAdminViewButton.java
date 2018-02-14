package org.pes.onecemulator.ui.menu;

import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import org.pes.onecemulator.view.invoiceadmin.root.view.InvoiceAdminView;

public class ToInvoiceAdminViewButton extends Button {

    private final static String ID = "ToInvoiceAdminViewButton";

    ToInvoiceAdminViewButton() {
        setId(ID);
        setCaption(InvoiceAdminView.CAPTION);
        setPrimaryStyleName(ValoTheme.MENU_ITEM);
        addClickListener(event -> getUI().getNavigator().navigateTo(InvoiceAdminView.VIEW_NAME));
    }

    void selected() {
        addStyleName("selected");
    }

    void unselected() {
        removeStyleName("selected");
    }
}
