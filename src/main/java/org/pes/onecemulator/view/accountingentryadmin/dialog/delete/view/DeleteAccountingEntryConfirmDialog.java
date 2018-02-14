package org.pes.onecemulator.view.accountingentryadmin.dialog.delete.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.accountingentryadmin.dialog.delete.presenter.IDeleteAccountingEntryConfirmDialog;
import org.pes.onecemulator.view.accountingentryadmin.dialog.delete.presenter.IDeleteAccountingEntryConfirmPresenter;
import org.pes.onecemulator.view.accountingentryadmin.root.presenter.IAccountingEntryAdminView;
import org.pes.onecemulator.view.accountingentryadmin.root.view.AccountingEntryAdminView;
import org.pes.onecemulator.view.fundamentals.dialog.confirm.ConfirmDialog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = DeleteAccountingEntryConfirmDialog.VIEW_NAME)
public class DeleteAccountingEntryConfirmDialog extends ConfirmDialog implements View, IDeleteAccountingEntryConfirmDialog {

    public final static String VIEW_NAME = "DeletePayerConfirmDialog";

    private final IDeleteAccountingEntryConfirmPresenter presenter;

    @Autowired
    public DeleteAccountingEntryConfirmDialog(IDeleteAccountingEntryConfirmPresenter presenter) {
        super("Вы действительно хотите удалить заявку на расход?");
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
        addClickListenerToCancelButton(e -> presenter.onClickCancelButton());
    }

    @Override
    public void returnAccountingEntryAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(AccountingEntryAdminView.VIEW_NAME);
    }
}
