package org.pes.onecemulator.converter.onec;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.model.onec.PayerModel;

public class PayerModelConverter implements Converter<Payer, PayerModel> {

    @Override
    public PayerModel convert(final Payer entity) {
        final PayerModel model = new PayerModel();
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
