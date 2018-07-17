package org.pes.onecemulator.ui.menu;

import com.vaadin.navigator.View;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SideMenu extends CssLayout {

    private final ToTopViewButton toTopViewButton = new ToTopViewButton();

    private final ToPayerAdminViewButton toPayerAdminViewButton = new ToPayerAdminViewButton();

    private final ToSourceAdminViewButton toSourceAdminViewButton = new ToSourceAdminViewButton();

    private final ToInvoiceAdminViewButton toInvoiceAdminViewButton = new ToInvoiceAdminViewButton();

    private final ToExpenseRequestAdminViewButton toExpenseRequestAdminViewButton = new ToExpenseRequestAdminViewButton();

    private final ToAccountingEntryAdminViewButton toAccountingEntryAdminViewButton = new ToAccountingEntryAdminViewButton();

    public SideMenu() {
        setPrimaryStyleName(ValoTheme.MENU_ROOT);
        Title title = new Title();
        addComponents(
                title,
                toTopViewButton,
                toSourceAdminViewButton,
                toPayerAdminViewButton,
                toInvoiceAdminViewButton,
                toExpenseRequestAdminViewButton,
                toAccountingEntryAdminViewButton);
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
        toExpenseRequestAdminViewButton.unselected();
        toAccountingEntryAdminViewButton.unselected();
    }

    private void selectedBy(String activeViewCaption) {
        if (activeViewCaption.equals(toTopViewButton.getCaption())) toTopViewButton.selected();
        if (activeViewCaption.equals(toSourceAdminViewButton.getCaption())) toSourceAdminViewButton.selected();
        if (activeViewCaption.equals(toPayerAdminViewButton.getCaption())) toPayerAdminViewButton.selected();
        if (activeViewCaption.equals(toInvoiceAdminViewButton.getCaption())) toInvoiceAdminViewButton.selected();
        if (activeViewCaption.equals(toExpenseRequestAdminViewButton.getCaption())) toExpenseRequestAdminViewButton.selected();
        if (activeViewCaption.equals(toAccountingEntryAdminViewButton.getCaption())) toAccountingEntryAdminViewButton.selected();
    }
}
