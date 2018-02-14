package org.pes.onecemulator.service;

import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestService {

    ExpenseRequestModel getById(UUID id);

    List<ExpenseRequestModel> list();

    ExpenseRequestModel create(ExpenseRequestModel model);

    ExpenseRequestModel update(ExpenseRequestModel model);

    void delete(UUID id);
}
