package org.pes.onecemulator.converter.internal;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.model.internal.InvoiceModel;

public class InvoiceModelConverter implements Converter<Invoice, InvoiceModel> {

    @Override
    public InvoiceModel convert(final Invoice entity) {
        final InvoiceModel model = new InvoiceModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setDate(entity.getDate());
        model.setNumber(entity.getNumber());
        model.setPayerCode(entity.getPayer().getCode());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSumInvoiceCurrency(entity.getPaymentSumInvoiceCurrency());
        model.setStatus(entity.getStatus());
        model.setSum(entity.getSum());
        model.setExternalId(entity.getExternalId());
        model.setPaymentCurrency(entity.getPaymentCurrency());
        model.setCurrency(entity.getCurrency());
        model.setPaymentSum(entity.getPaymentSum());
        return model;
    }
}
