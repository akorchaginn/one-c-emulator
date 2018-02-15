package org.pes.onecemulator.service;

import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;

import java.util.List;
import java.util.UUID;

public interface SourceService {

    SourceModel getById(UUID id);

    SourceModel getByName(String name);

    List<SourceModel> list();

    List<PayerModel> getPayerList(String name);

    SourceModel create(SourceModel model);

    SourceModel update(SourceModel model);

    void delete(UUID id);
}
