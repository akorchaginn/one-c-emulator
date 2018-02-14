package org.pes.onecemulator.view.payeradmin.dialog.delete.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.view.fundamentals.dialog.confirm.ConfirmDialog;
import org.pes.onecemulator.view.payeradmin.dialog.delete.presenter.IDeletePayerConfirmDialog;
import org.pes.onecemulator.view.payeradmin.dialog.delete.presenter.IDeletePayerConfirmPresenter;
import org.pes.onecemulator.view.payeradmin.root.presenter.IPayerAdminView;
import org.pes.onecemulator.view.payeradmin.root.view.PayerAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = DeletePayerConfirmDialog.VIEW_NAME)
public class DeletePayerConfirmDialog extends ConfirmDialog implements View, IDeletePayerConfirmDialog {

    public final static String VIEW_NAME = "DeletePayerConfirmDialog";

    private final IDeletePayerConfirmPresenter presenter;

    @Autowired
    public DeletePayerConfirmDialog(IDeletePayerConfirmPresenter presenter) {
        super("Вы действительно хотите удалить плательщика?");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        IPayerAdminView sourceView = (IPayerAdminView) event.getOldView();
        addClickListenerToOkButton(e -> presenter.onClickOkButton(sourceView.allGridSelections()));
        addClickListenerToCancelButton(e -> presenter.onClickCancelButton());
    }

    @Override
    public void returnPayerAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(PayerAdminView.VIEW_NAME);
    }
}
