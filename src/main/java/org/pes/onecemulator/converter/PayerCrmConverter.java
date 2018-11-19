package org.pes.onecemulator.converter;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.model.PayerCrm;

public class PayerCrmConverter implements Converter<Payer, PayerCrm> {

    @Override
    public PayerCrm convert(final Payer entity) {
        final PayerCrm model = new PayerCrm();
        model.setId(entity.getAddress());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());
        model.setAddress(entity.getAddress());

        return model;
    }
}
