package org.pes.onecemulator.view.accountingentryadmin.dialog.delete;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.accountingentryadmin.root.presenter.IAccountingEntryAdminPresenter;
import org.pes.onecemulator.view.accountingentryadmin.root.view.IAccountingEntryAdminView;
import org.pes.onecemulator.view.accountingentryadmin.root.view.AccountingEntryAdminView;
import org.pes.onecemulator.view.fundamentals.dialog.confirm.ConfirmDialog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = DeleteAccountingEntryConfirmDialog.VIEW_NAME)
public class DeleteAccountingEntryConfirmDialog extends ConfirmDialog implements View, IDeleteAccountingEntryConfirmDialog {

    public final static String VIEW_NAME = "DeleteAccountingEntryConfirmDialog";

    private final IAccountingEntryAdminPresenter presenter;

    @Autowired
    public DeleteAccountingEntryConfirmDialog(IAccountingEntryAdminPresenter presenter) {
        super("Вы действительно хотите удалить проводку?");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        IAccountingEntryAdminView accountingEntryView = (IAccountingEntryAdminView) event.getOldView();
        addClickListenerToOkButton(e -> presenter.onClickOkButton(accountingEntryView.allGridSelections()));
        addClickListenerToCancelButton(e -> returnAccountingEntryAdminView());
    }

    @Override
    public void returnAccountingEntryAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(AccountingEntryAdminView.VIEW_NAME);
    }
}
