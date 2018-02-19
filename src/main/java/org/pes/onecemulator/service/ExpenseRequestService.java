package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.ExpenseRequestModel;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestService {

    ExpenseRequestModel getById(UUID id) throws NotFoundException;

    List<ExpenseRequestModel> list();

    ExpenseRequestModel create(ExpenseRequestModel model) throws Exception;

    ExpenseRequestModel update(ExpenseRequestModel model) throws Exception;

    void delete(UUID id);
}
