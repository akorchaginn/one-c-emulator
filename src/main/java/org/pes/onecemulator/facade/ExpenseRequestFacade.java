package org.pes.onecemulator.facade;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestFacade {

    List<ExpenseRequestModel> list();

    List<String> listNumbers();

    ExpenseRequestModel create(ExpenseRequestModel model) throws NotFoundException, ValidationException;

    ExpenseRequestModel update(ExpenseRequestModel model) throws NotFoundException, ValidationException;

    void delete(UUID id);
}
