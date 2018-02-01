package org.pes.onecemulator.service;

import org.pes.onecemulator.model.ERequestModel;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestService {

    ERequestModel getById(UUID id);

    List<ERequestModel> list();

    ERequestModel create(ERequestModel model);

    ERequestModel update(ERequestModel model);

    void delete(UUID id);
}
