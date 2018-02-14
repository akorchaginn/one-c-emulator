package org.pes.onecemulator.view.payeradmin.root.view.control;

import com.vaadin.ui.TextField;

public class SearchTextField extends TextField {

    private final static String ID = "SearchTextField";

    SearchTextField() {
        setId(ID);
        setPlaceholder("код, название, бд 1с");
    }
}
