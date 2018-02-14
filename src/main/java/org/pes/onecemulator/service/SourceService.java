package org.pes.onecemulator.service;

import org.pes.onecemulator.model.SourceModel;

import java.util.List;
import java.util.UUID;

public interface SourceService {

    SourceModel getById(UUID id);

    List<SourceModel> list();

    SourceModel create(SourceModel model);

    SourceModel update(SourceModel model);

    void delete(UUID id);
}
