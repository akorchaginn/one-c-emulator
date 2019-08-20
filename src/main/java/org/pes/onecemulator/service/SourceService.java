package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.SourceModel;

import java.util.List;
import java.util.UUID;

public interface SourceService {

    Source getById(UUID id) throws NotFoundException;

    Source getByName(String name) throws NotFoundException;

    List<Source> list();

    Source create(SourceModel model) throws ValidationException;

    Source update(SourceModel model) throws ValidationException, NotFoundException;

    void delete(UUID id);
}
