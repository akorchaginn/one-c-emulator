package org.pes.onecemulator.view.fundamentals.control;

import com.vaadin.ui.TextField;

public class SearchTextField extends TextField {

    private final static String ID = "SearchTextField";

    SearchTextField(String placeholder) {
        setId(ID);
        setPlaceholder(placeholder);
    }
}
