package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestService {

    ExpenseRequest getById(UUID id) throws NotFoundException;

    List<ExpenseRequest> list();

    ExpenseRequest create(ExpenseRequestModel model) throws ValidationException, NotFoundException;

    ExpenseRequest update(ExpenseRequestModel model) throws ValidationException, NotFoundException;

    void delete(UUID id);
}
