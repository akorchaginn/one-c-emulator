package org.pes.onecemulator.ui.view.fundamentals.dialog.confirm;

import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class ConfirmDialog extends Window {

    private final OkCancelButtons buttons = new OkCancelButtons();

    public ConfirmDialog(final String message) {
        ConfirmMessageDisplay confirmMessageDisplay = new ConfirmMessageDisplay();
        confirmMessageDisplay.setValue(message);

        VerticalLayout body = new VerticalLayout();
        body.addComponents(confirmMessageDisplay, buttons);

        setContent(body);
        setCaption("Подтвердите действие");
        setModal(true);
        setResizable(false);
        setWidth(300, Unit.PIXELS);
        setClosable(false);
        setWindowMode(WindowMode.NORMAL);
    }

    protected void addClickListenerToOkButton(ClickListener clickListener) {
        buttons.okButton.addClickListener(clickListener);
    }

    protected void addClickListenerToCancelButton(ClickListener clickListener) {
        buttons.cancelButton.addClickListener(clickListener);
    }
}
