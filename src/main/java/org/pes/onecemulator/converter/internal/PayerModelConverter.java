package org.pes.onecemulator.converter.internal;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.PayerSource;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.stream.Collectors;

public class PayerModelConverter implements Converter<Payer, PayerModel> {

    @Override
    public PayerModel convert(final Payer entity) {
        final PayerModel model = new PayerModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());
        model.setAddress(entity.getAddress());
        model.getSources().addAll(entity.getPayerSources()
                .stream()
                .map(PayerSource::getSource)
                .map(Source::getName)
                .collect(Collectors.toSet()));

        return model;
    }
}
