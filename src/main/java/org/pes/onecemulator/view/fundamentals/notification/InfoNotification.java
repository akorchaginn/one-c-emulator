package org.pes.onecemulator.view.fundamentals.notification;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

final class InfoNotification {

    public static void show(final String description) {
        Notification error = new Notification("Информация:", description, Notification.Type.HUMANIZED_MESSAGE);
        error.setDelayMsec(10000);
        error.setPosition(Position.TOP_RIGHT);
        error.show(Page.getCurrent());
    }
}
