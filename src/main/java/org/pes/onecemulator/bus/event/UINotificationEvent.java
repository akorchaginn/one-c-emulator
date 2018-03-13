package org.pes.onecemulator.bus.event;

public class UINotificationEvent {

    private final Object source;

    private final String description;

    private final Boolean error;

    public UINotificationEvent(Object source, String description, Boolean error) {
        this.source = source;
        this.description = description;
        this.error = error;
    }

    public Object getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getError() { return error; }
}
