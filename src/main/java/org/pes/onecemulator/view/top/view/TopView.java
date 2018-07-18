package org.pes.onecemulator.view.top.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.view.fundamentals.layout.BaseViewLayout;

@SpringView(name = TopView.VIEW_NAME)
public class TopView extends BaseViewLayout implements View {

    public static final String VIEW_NAME = ""; // need top view name is ""

    public static final String CAPTION = "Главная";

    private static final String TITLE = "1C-emulator: Главная";

    public TopView() {
        super();
        ViewHeader viewHeader = new ViewHeader(CAPTION);
        TopViewBody viewBody = new TopViewBody();
        addHeaderAndBody(viewHeader, viewBody);
        setCaption(CAPTION);
        UI.getCurrent().getPage().setTitle(TITLE);
    }
}
