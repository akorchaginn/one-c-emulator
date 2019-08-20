package org.pes.onecemulator.facade.impl;

import org.pes.onecemulator.converter.internal.SourceModelConverter;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.facade.SourceFacade;
import org.pes.onecemulator.model.internal.SourceModel;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SourceFacadeImpl implements SourceFacade {

    private static final SourceModelConverter SOURCE_MODEL_CONVERTER = new SourceModelConverter();

    private final SourceService sourceService;

    @Autowired
    public SourceFacadeImpl(final SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @Override
    public List<SourceModel> list() {
        return sourceService.list().stream()
                .map(SOURCE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listNames() {
        return sourceService.list().stream()
                .map(Source::getName)
                .collect(Collectors.toList());
    }

    @Override
    public SourceModel create(final SourceModel sourceModel) throws ValidationException {
        return SOURCE_MODEL_CONVERTER.convert(sourceService.create(sourceModel));
    }

    @Override
    public SourceModel update(final SourceModel sourceModel) throws ValidationException, NotFoundException {
        return SOURCE_MODEL_CONVERTER.convert(sourceService.update(sourceModel));
    }

    @Override
    public void delete(final UUID id) {
        sourceService.delete(id);
    }

}
