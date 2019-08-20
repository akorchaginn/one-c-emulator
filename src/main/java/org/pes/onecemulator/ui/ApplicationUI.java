package org.pes.onecemulator.ui;

import com.vaadin.annotations.Push;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.pes.onecemulator.ui.view.fundamentals.notification.ErrorNotification;

@Push(transport = Transport.WEBSOCKET_XHR)
@SpringUI
@SpringViewDisplay
public class ApplicationUI extends UI implements ViewDisplay {

    private final ApplicationUIContent content;

    public ApplicationUI() {
        this.content = new ApplicationUIContent();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(content);
        setSizeFull();
        addStyleName(ValoTheme.UI_WITH_MENU);
        getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                ErrorNotification.show(event.getThrowable());
                doDefault(event);
            }
        });
    }

    @Override
    public void showView(View view) {
        if (view instanceof Window) {
            addWindow((Window) view);
            return;
        }
        content.getSideMenu().selectedItemRelatedTo(view);
        content.getViewDisplay().setContent(view.getViewComponent());
    }
}
