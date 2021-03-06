package org.pes.onecemulator.ui.view.invoiceadmin.dialog.delete;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.UI;
import org.pes.onecemulator.ui.view.fundamentals.dialog.confirm.ConfirmDialog;
import org.pes.onecemulator.ui.view.invoiceadmin.root.presenter.IInvoiceAdminPresenter;
import org.pes.onecemulator.ui.view.invoiceadmin.root.view.IInvoiceAdminView;
import org.pes.onecemulator.ui.view.invoiceadmin.root.view.InvoiceAdminView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = DeleteInvoiceConfirmDialog.VIEW_NAME)
public class DeleteInvoiceConfirmDialog extends ConfirmDialog implements View, IDeleteInvoiceConfirmDialog {

    public final static String VIEW_NAME = "DeleteInvoiceConfirmDialog";

    private final IInvoiceAdminPresenter presenter;

    @Autowired
    public DeleteInvoiceConfirmDialog(IInvoiceAdminPresenter presenter) {
        super("Вы действительно хотите удалить счет?");
        this.presenter = presenter;
    }

    @PostConstruct
    void init() {
        presenter.attachView(this);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        IInvoiceAdminView invoiceView = (IInvoiceAdminView) event.getOldView();
        addClickListenerToOkButton(e -> presenter.onClickOkButton(invoiceView.allGridSelections()));
        addClickListenerToCancelButton(e -> returnInvoiceAdminView());
    }

    @Override
    public void returnInvoiceAdminView() {
        close();
        UI.getCurrent().getNavigator().navigateTo(InvoiceAdminView.VIEW_NAME);
    }
}
