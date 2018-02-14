package org.pes.onecemulator.view.payeradmin.root.view.control;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AddEditDeleteButtons extends CssLayout {

    final AddButton addButton = new AddButton();

    final EditButton editButton = new EditButton();

    final DeleteButton deleteButton = new DeleteButton();

    AddEditDeleteButtons() {
        addComponents(addButton, editButton, deleteButton);
        addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        editButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
}
