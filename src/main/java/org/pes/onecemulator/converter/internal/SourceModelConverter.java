package org.pes.onecemulator.converter.internal;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.internal.SourceModel;

public class SourceModelConverter implements Converter<Source, SourceModel> {

    @Override
    public SourceModel convert(final Source entity) {
        final SourceModel model = new SourceModel();
        model.setId(entity.getId());
        model.setName(entity.getName());

        return model;
    }
}
