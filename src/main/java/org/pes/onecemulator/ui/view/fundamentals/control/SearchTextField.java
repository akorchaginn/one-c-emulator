package org.pes.onecemulator.ui.view.fundamentals.control;

import com.vaadin.ui.TextField;

class SearchTextField extends TextField {

    private final static String ID = "SearchTextField";

    SearchTextField(String placeholder) {
        setId(ID);
        setPlaceholder(placeholder);
    }
}
