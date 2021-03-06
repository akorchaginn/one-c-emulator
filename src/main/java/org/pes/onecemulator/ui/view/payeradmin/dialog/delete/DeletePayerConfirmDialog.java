package org.pes.onecemulator.ui.view.payeradmin.dialog.delete;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.ui.view.fundamentals.dialog.confirm.ConfirmDialog;
import org.pes.onecemulator.ui.view.payeradmin.root.presenter.IPayerAdminPresenter;
import org.pes.onecemulator.ui.view.payeradmin.root.view.IPayerAdminView;
import org.pes.onecemulator.ui.view.payeradmin.root.view.PayerAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = DeletePayerConfirmDialog.VIEW_NAME)
public class DeletePayerConfirmDialog extends ConfirmDialog implements View, IDeletePayerConfirmDialog {

    public final static String VIEW_NAME = "DeletePayerConfirmDialog";

    private final IPayerAdminPresenter presenter;

    @Autowired
    public DeletePayerConfirmDialog(IPayerAdminPresenter presenter) {
        super("Вы действительно хотите удалить контрагента?");
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
        addClickListenerToCancelButton(e -> returnPayerAdminView());
    }

    @Override
    public void returnPayerAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(PayerAdminView.VIEW_NAME);
    }
}
