package org.pes.onecemulator.converter;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.SourceModel;

public class SourceModelConverter implements Converter<Source, SourceModel> {

    @Override
    public SourceModel convert(Source entity) {
        final SourceModel model = new SourceModel();
        model.setId(entity.getId());
        model.setName(entity.getName());

        return model;
    }
}
