package org.pes.onecemulator.converter;

import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.model.InvoiceModel;

public class InvoiceModelConverter implements Converter<Invoice, InvoiceModel> {

    @Override
    public InvoiceModel convert(Invoice entity) {
        final InvoiceModel model = new InvoiceModel();
        model.setId(entity.getId());
        model.setSource(entity.getSource().getName());
        model.setDate(entity.getDate());
        model.setNumber(entity.getNumber());
        model.setNumberOq(entity.getNumberOq());
        model.setPayerCode(entity.getPayer().getCode());
        model.setPaymentDate(entity.getPaymentDate());
        model.setPaymentSumRUB(entity.getPaymentSumRUB());
        model.setStatus(entity.getStatus());
        model.setSum(entity.getSum());
        model.setExternalId(entity.getExternalId());
        model.setPaymentCurrency(entity.getPaymentCurrency());
        model.setCurrency(entity.getCurrency());
        model.setPaymentSum(entity.getPaymentSum());
        model.setSumRUB(entity.getSumRUB());

        return model;
    }
}
