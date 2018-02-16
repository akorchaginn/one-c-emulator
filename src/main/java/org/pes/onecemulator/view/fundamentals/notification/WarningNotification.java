package org.pes.onecemulator.view.fundamentals.notification;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

public final class WarningNotification {

    public static void show(String description) {
        Notification error =
                new Notification("Предупреждение:",description, Notification.Type.WARNING_MESSAGE);
        error.setDelayMsec(3000);
        error.setPosition(Position.TOP_RIGHT);
        error.show(Page.getCurrent());
    }
}
