package org.pes.onecemulator.view.expenserequestadmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.view.expenserequestadmin.root.view.grid.ExpenseRequestGrid;
import org.pes.onecemulator.view.fundamentals.control.ControlArea;

public class ExpenseRequestAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea("номер, сумма");

    final ExpenseRequestGrid expenseRequestGrid = new ExpenseRequestGrid();

    ExpenseRequestAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, expenseRequestGrid);
        setExpandRatio(expenseRequestGrid, 1.0f);
    }
}
