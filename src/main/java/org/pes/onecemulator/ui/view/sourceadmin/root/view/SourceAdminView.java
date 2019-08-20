package org.pes.onecemulator.ui.view.sourceadmin.root.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.model.internal.SourceModel;
import org.pes.onecemulator.ui.view.fundamentals.fragment.header.ViewHeader;
import org.pes.onecemulator.ui.view.fundamentals.layout.BaseViewLayout;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.add.SourceAddDialog;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.delete.DeleteSourceConfirmDialog;
import org.pes.onecemulator.ui.view.sourceadmin.dialog.edit.SourceEditDialog;
import org.pes.onecemulator.ui.view.sourceadmin.root.presenter.ISourceAdminPresenter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = SourceAdminView.VIEW_NAME)
public class SourceAdminView extends BaseViewLayout implements View, ISourceAdminView {

    public static final String VIEW_NAME = "SourceAdminView";

    public static final String CAPTION = "Базы 1С";

    private static final String TITLE = "1C-emulator: " + CAPTION;

    private final SourceAdminViewBody viewBody = new SourceAdminViewBody();

    private final ISourceAdminPresenter presenter;

    @Autowired
    public SourceAdminView(ISourceAdminPresenter presenter) {
        // 1st construction
        super();
        this.presenter = presenter;
        setCaption(CAPTION);
        UI.getCurrent().getPage().setTitle(TITLE);
        ViewHeader viewHeader = new ViewHeader(CAPTION);
        addHeaderAndBody(viewHeader, viewBody);
    }

    @PostConstruct
    void init() {
        // 2nd construction
        presenter.attachView(this);
        presenter.loadSourceList();
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // 3rd construction
        viewBody.controlArea.addClickEventListenerToSearchButton(e -> presenter.onClickSearchButton());
        viewBody.controlArea.addClickEventListenerToAddButton(e -> presenter.onClickAddButton());
        viewBody.controlArea.addClickEventListenerToEditButton(e -> presenter.onClickEditButton());
        viewBody.controlArea.addClickEventListenerToDeleteButton(e -> presenter.onClickDeleteButton());
        viewBody.sourceGrid.addSelectionListener(e -> presenter.onSelectGrid());
    }

    @Override
    public void bindingGridData(List<SourceModel> sourceModelList) {
        viewBody.sourceGrid.binding(sourceModelList);
    }

    @Override
    public void doFilterBySearchText() {
        viewBody.sourceGrid.filterBy(viewBody.controlArea.searchText());
    }

    @Override
    public void toStateOfOnlyCanAdd() {
        viewBody.controlArea.toStateOfOnlyCanAdd();
    }

    @Override
    public void toStateOfCanAll() {
        viewBody.controlArea.toStateOfCanAll();
    }

    @Override
    public void toStateOfCanAddAndDelete() {
        viewBody.controlArea.toStateOfCanAddAndDelete();
    }

    @Override
    public List<SourceModel> allGridSelections() {
        return viewBody.sourceGrid.allSelections();
    }

    @Override
    public SourceModel gridSelection() {
        return viewBody.sourceGrid.selection();
    }

    @Override
    public void launchSourceAddDialog() {
        getUI().getNavigator().navigateTo(SourceAddDialog.VIEW_NAME);
    }

    @Override
    public void launchSourceEditDialog() {
        getUI().getNavigator().navigateTo(SourceEditDialog.VIEW_NAME);
    }

    @Override
    public void launchDeleteSourceConfirmDialog() {
        getUI().getNavigator().navigateTo(DeleteSourceConfirmDialog.VIEW_NAME);
    }
}
