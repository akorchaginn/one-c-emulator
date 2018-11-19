package org.pes.onecemulator.converter;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.PayerModel;

import java.util.Set;
import java.util.stream.Collectors;

public class PayerModelConverter implements Converter<Payer, PayerModel> {

    @Override
    public PayerModel convert(Payer entity) {
        final PayerModel model = new PayerModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setName(entity.getName());
        model.setFullName(entity.getFullName());
        model.setInn(entity.getInn());
        model.setKpp(entity.getKpp());
        model.setAddress(entity.getAddress());
        final Set<String> sources = entity.getSources()
                .stream()
                .map(Source::getName)
                .collect(Collectors.toSet());
        model.getSources().addAll(sources);

        return model;
    }
}
