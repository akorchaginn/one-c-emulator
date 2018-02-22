package org.pes.onecemulator.view.accountingentryadmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.view.accountingentryadmin.root.view.grid.AccountingEntryGrid;
import org.pes.onecemulator.view.fundamentals.control.ControlArea;

public class AccountingEntryAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea("код, номер заявки на расход");

    final AccountingEntryGrid accountingEntryGrid = new AccountingEntryGrid();

    AccountingEntryAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, accountingEntryGrid);
        setExpandRatio(accountingEntryGrid, 1.0f);
    }
}
