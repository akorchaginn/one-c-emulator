package org.pes.onecemulator.view.invoiceadmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.view.invoiceadmin.root.view.control.ControlArea;
import org.pes.onecemulator.view.invoiceadmin.root.view.grid.InvoiceGrid;

public class InvoiceAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea();

    final InvoiceGrid invoiceGrid = new InvoiceGrid();

    InvoiceAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, invoiceGrid);
        setExpandRatio(invoiceGrid, 1.0f);
    }
}
