package org.pes.onecemulator.ui.menu;

import com.vaadin.navigator.View;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SideMenu extends CssLayout {

    private final Title title = new Title();

    private final ToTopViewButton toTopViewButton = new ToTopViewButton();

    private final ToPayerAdminViewButton toPayerAdminViewButton = new ToPayerAdminViewButton();

    private final ToSourceAdminViewButton toSourceAdminViewButton = new ToSourceAdminViewButton();

    private final ToInvoiceAdminViewButton toInvoiceAdminViewButton = new ToInvoiceAdminViewButton();

    public SideMenu() {
        setPrimaryStyleName(ValoTheme.MENU_ROOT);
        addComponents(title, toTopViewButton, toSourceAdminViewButton, toPayerAdminViewButton, toInvoiceAdminViewButton);
    }

    public void selectedItemRelatedTo(View activeView) {
        allSelectedClear();
        selectedBy(activeView.getViewComponent().getCaption());
    }

    private void allSelectedClear() {
        toTopViewButton.unselected();
        toSourceAdminViewButton.unselected();
        toPayerAdminViewButton.unselected();
        toInvoiceAdminViewButton.unselected();
    }

    private void selectedBy(String activeViewCaption) {
        if (activeViewCaption.equals(toTopViewButton.getCaption())) toTopViewButton.selected();
        if (activeViewCaption.equals(toSourceAdminViewButton.getCaption())) toSourceAdminViewButton.selected();
        if (activeViewCaption.equals(toPayerAdminViewButton.getCaption())) toPayerAdminViewButton.selected();
        if (activeViewCaption.equals(toInvoiceAdminViewButton.getCaption())) toInvoiceAdminViewButton.selected();
    }
}
