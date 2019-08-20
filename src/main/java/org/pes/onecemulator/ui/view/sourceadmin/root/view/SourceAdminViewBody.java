package org.pes.onecemulator.ui.view.sourceadmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.ui.view.fundamentals.control.ControlArea;
import org.pes.onecemulator.ui.view.sourceadmin.root.view.grid.SourceGrid;

class SourceAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea("название");

    final SourceGrid sourceGrid = new SourceGrid();

    SourceAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, sourceGrid);
        setExpandRatio(sourceGrid, 1.0f);
    }
}
