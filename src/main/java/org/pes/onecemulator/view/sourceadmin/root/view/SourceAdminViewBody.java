package org.pes.onecemulator.view.sourceadmin.root.view;

import com.vaadin.ui.VerticalLayout;
import org.pes.onecemulator.view.sourceadmin.root.view.control.ControlArea;
import org.pes.onecemulator.view.sourceadmin.root.view.grid.SourceGrid;

public class SourceAdminViewBody extends VerticalLayout {

    final ControlArea controlArea = new ControlArea();

    final SourceGrid sourceGrid = new SourceGrid();

    SourceAdminViewBody() {
        setSizeFull();
        setMargin(false);
        addComponents(controlArea, sourceGrid);
        setExpandRatio(sourceGrid, 1.0f);
    }
}
