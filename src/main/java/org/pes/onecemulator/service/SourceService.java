package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.SourceModel;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SourceService {

    SourceModel getById(UUID id) throws NotFoundException;

    SourceModel getByName(String name) throws NotFoundException;

    List<SourceModel> list();

    SourceModel create(SourceModel model) throws Exception;

    Set<Source> updateOrCreate(final Set<String> sources);

    SourceModel update(SourceModel model) throws Exception;

    void delete(UUID id);
}
