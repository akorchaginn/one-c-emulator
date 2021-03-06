package org.pes.onecemulator.ui.view.expenserequestadmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.ui.view.expenserequestadmin.root.view.grid.ExpenseRequestGrid;
import org.pes.onecemulator.ui.view.fundamentals.control.ControlArea;

class ExpenseRequestAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea("номер, сумма");

    final ExpenseRequestGrid expenseRequestGrid = new ExpenseRequestGrid();

    ExpenseRequestAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, expenseRequestGrid);
        setExpandRatio(expenseRequestGrid, 1.0f);
    }
}
