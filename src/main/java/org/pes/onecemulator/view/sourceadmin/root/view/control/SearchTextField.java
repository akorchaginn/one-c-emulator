package org.pes.onecemulator.view.sourceadmin.root.view.control;

import com.vaadin.ui.TextField;

public class SearchTextField extends TextField {

    private final static String ID = "SearchTextField";

    SearchTextField() {
        setId(ID);
        setPlaceholder("название");
    }
}
