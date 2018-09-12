package org.pes.onecemulator.event.ui;

import org.springframework.context.ApplicationEvent;

public class UINotificationEvent extends ApplicationEvent {

    private final Object source;

    private final String description;

    private final boolean error;

    public UINotificationEvent(final Object source, final String description, final boolean error) {
        super(source);
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

    public boolean getError() { return error; }
}