package org.pes.onecemulator.converter.onec;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.InvoiceItem;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.model.internal.EmployeeSourceModel;
import org.pes.onecemulator.model.onec.DocumentModel;
import org.pes.onecemulator.model.onec.InvoiceItemModel;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentModelConverter implements Converter<Invoice, DocumentModel> {

    @Override
    public DocumentModel convert(final Invoice entity) {
        final DocumentModel model = new DocumentModel();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setPayerName(Optional.ofNullable(entity.getPayer())
                .map(Payer::getName)
                .orElse(null));
        model.setInvoiceSum(entity.getSum());
        model.setDate(entity.getDate());
        model.setStatus(entity.getStatus());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSumInvoiceCurrency(entity.getPaymentSumInvoiceCurrency());
        model.setExternalId(entity.getExternalId());
        model.setInvoiceCurrency(entity.getCurrency());
        model.setPaymentCurrency(entity.getPaymentCurrency());
        model.setPaymentSum(entity.getPaymentSum());
        model.getInvoiceItems().addAll(getInvoiceItems(entity));
        return model;
    }

    private List<InvoiceItemModel> getInvoiceItems(final Invoice entity){
        return entity.getItems().stream()
                .map(es -> {
                    final InvoiceItemModel model = new InvoiceItemModel();
                    model.setId(es.getId());
                    model.setContent(es.getContent());
                    return model;
                })
                .collect(Collectors.toList());
    }
}
