package org.pes.onecemulator.bus.event;

public class UINotificationEvent {

    private final Object source;

    private final String description;

    public UINotificationEvent(Object source, String description) {
        this.source = source;
        this.description = description;
    }

    public Object getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }
}
