package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.model.SourceModel;

import java.util.List;
import java.util.UUID;

public interface SourceService {

    SourceModel getById(UUID id) throws NotFoundException;

    SourceModel getByName(String name) throws NotFoundException;

    List<SourceModel> list();

    List<PayerModel> getPayerList(String name) throws NotFoundException;

    SourceModel create(SourceModel model) throws Exception;

    SourceModel update(SourceModel model) throws Exception;

    void delete(UUID id);
}
