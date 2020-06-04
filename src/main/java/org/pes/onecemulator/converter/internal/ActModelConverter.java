package org.pes.onecemulator.converter.internal;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.AbstractEntity;
import org.pes.onecemulator.entity.Act;
import org.pes.onecemulator.model.internal.ActModel;

import java.util.stream.Collectors;

public class ActModelConverter implements Converter<Act, ActModel> {
    @Override
    public ActModel convert(final Act act) {
        final ActModel actModel = new ActModel();

        actModel.setId(act.getId());
        actModel.setAcceptDate(act.getAcceptDate());
        actModel.setNumber(act.getNumber());
        actModel.setStatus(act.getStatus());

        actModel.setInvoiceIds(act.getInvoices().stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toSet()));

        return actModel;
    }
}
