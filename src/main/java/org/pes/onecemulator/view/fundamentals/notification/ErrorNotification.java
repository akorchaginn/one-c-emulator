package org.pes.onecemulator.view.fundamentals.notification;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import org.apache.commons.lang3.exception.ExceptionUtils;

public final class ErrorNotification {

    public static void show(String description) {
        Notification error = new Notification("Ошибка:", description, Notification.Type.ERROR_MESSAGE);
        error.setDelayMsec(-1);
        error.setPosition(Position.TOP_RIGHT);
        error.show(Page.getCurrent());
    }

    public static void show(Exception e) {
        Notification error = new Notification(
                "Ошибка:",
                e.getMessage() + "\n" + ExceptionUtils.getRootCauseMessage(e),
                Notification.Type.ERROR_MESSAGE);
        error.setDelayMsec(-1);
        error.setPosition(Position.TOP_RIGHT);
        error.show(Page.getCurrent());
    }
}
