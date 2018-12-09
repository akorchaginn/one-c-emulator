package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.SourceModel;

import java.util.List;
import java.util.UUID;

public interface SourceService {

    SourceModel getById(UUID id) throws NotFoundException;

    SourceModel getByName(String name) throws NotFoundException;

    List<SourceModel> list();

    SourceModel create(SourceModel model) throws ValidationException;

    SourceModel update(SourceModel model) throws ValidationException, NotFoundException;

    void delete(UUID id);
}
