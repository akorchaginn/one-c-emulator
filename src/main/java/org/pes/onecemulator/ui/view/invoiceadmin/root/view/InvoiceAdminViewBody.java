package org.pes.onecemulator.ui.view.invoiceadmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.ui.view.fundamentals.control.ControlArea;
import org.pes.onecemulator.ui.view.invoiceadmin.root.view.grid.InvoiceGrid;

class InvoiceAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea("номер, внешний идентификатор");

    final InvoiceGrid invoiceGrid = new InvoiceGrid();

    InvoiceAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, invoiceGrid);
        setExpandRatio(invoiceGrid, 1.0f);
    }
}
