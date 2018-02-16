package org.pes.onecemulator.ui;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Push;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.pes.onecemulator.bus.event.UINotificationEvent;
import org.pes.onecemulator.view.fundamentals.notification.InfoNotification;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Push(transport = Transport.WEBSOCKET_XHR)
@SpringUI
@SpringViewDisplay
public class ApplicationUI extends UI implements ViewDisplay {

    private final ApplicationUIContent content = new ApplicationUIContent();

    private final AsyncEventBus asyncEventBus;

    @Autowired
    ApplicationUI(AsyncEventBus asyncEventBus) {
        this.asyncEventBus = asyncEventBus;
    }

    @PostConstruct
    public void init() {
        asyncEventBus.register(this);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(content);
        setSizeFull();
        addStyleName(ValoTheme.UI_WITH_MENU);
    }

    @Override
    public void showView(View view) {
        if (view instanceof Window) {
            addWindow((Window) view);
            return;
        }
        content.sideMenu.selectedItemRelatedTo(view);
        content.viewDisplay.setContent(view.getViewComponent());
    }

    @Subscribe
    public void processUINotification(UINotificationEvent event) {
        if (event.getDescription() != null) {
            access(() -> InfoNotification.show(event.getDescription()));
        }
    }
}
