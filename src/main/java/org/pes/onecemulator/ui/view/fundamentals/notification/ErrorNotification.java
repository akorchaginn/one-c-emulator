package org.pes.onecemulator.ui.view.fundamentals.notification;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import org.pes.onecemulator.exception.util.ExceptionUtils;

public final class ErrorNotification {

    public static void show(final Throwable throwable) {
        final Notification error = new Notification(
                "Ошибка: ",
                ExceptionUtils.getCause(throwable).getMessage() + "\n -> " + throwable,
                Notification.Type.ERROR_MESSAGE);
        error.setDelayMsec(-1);
        error.setPosition(Position.TOP_RIGHT);
        error.show(Page.getCurrent());
    }
}
