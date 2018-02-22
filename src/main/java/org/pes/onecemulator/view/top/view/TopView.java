package org.pes.onecemulator.view.top.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import org.pes.onecemulator.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.view.fundamentals.layout.BaseViewLayout;
import com.vaadin.ui.UI;

@SpringView(name = TopView.VIEW_NAME)
public class TopView extends BaseViewLayout implements View {

    public static final String VIEW_NAME = ""; // need top view name is ""

    public static final String CAPTION = "Главная";

    public static final String TITLE = "1C-emulator: Главная";

    private final ViewHeader viewHeader = new ViewHeader(CAPTION);

    private final TopViewBody viewBody = new TopViewBody();

    public TopView() {
        super();
        addHeaderAndBody(viewHeader, viewBody);
        setCaption(CAPTION);
        UI.getCurrent().getPage().setTitle(TITLE);
    }
}
