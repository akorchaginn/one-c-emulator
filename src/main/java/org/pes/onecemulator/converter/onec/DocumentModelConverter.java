package org.pes.onecemulator.converter.onec;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.model.onec.DocumentModel;

import java.util.Optional;

public class DocumentModelConverter implements Converter<Invoice, DocumentModel> {

    @Override
    public DocumentModel convert(final Invoice entity) {
        final DocumentModel model = new DocumentModel();
        model.setId(entity.getId());
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setPayerName(Optional.ofNullable(entity.getPayer())
                .map(Payer::getName)
                .orElse(null));
        model.setInvoiceSum(entity.getSum());
        model.setDate(entity.getDate());
        model.setStatus(entity.getStatus());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSumRUB(entity.getPaymentSumRUB());
        model.setExternalId(entity.getExternalId());
        model.setInvoiceSumRUB(entity.getSumRUB());
        model.setInvoiceCurrency(entity.getCurrency());
        model.setPaymentCurrency(entity.getPaymentCurrency());
        model.setPaymentSum(entity.getPaymentSum());

        return model;
    }
}
