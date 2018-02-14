package org.pes.onecemulator.view.payeradmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.view.payeradmin.root.view.control.ControlArea;
import org.pes.onecemulator.view.payeradmin.root.view.grid.PayerGrid;

public class PayerAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea();

    final PayerGrid payerGrid = new PayerGrid();

    PayerAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, payerGrid);
        setExpandRatio(payerGrid, 1.0f);
    }
}
