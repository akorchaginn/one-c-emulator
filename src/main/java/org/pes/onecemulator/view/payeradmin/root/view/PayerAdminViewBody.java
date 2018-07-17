package org.pes.onecemulator.view.payeradmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.view.fundamentals.control.ControlArea;
import org.pes.onecemulator.view.payeradmin.root.view.grid.PayerGrid;

class PayerAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea("код, название, бд 1с");

    final PayerGrid payerGrid = new PayerGrid();

    PayerAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, payerGrid);
        setExpandRatio(payerGrid, 1.0f);
    }
}
