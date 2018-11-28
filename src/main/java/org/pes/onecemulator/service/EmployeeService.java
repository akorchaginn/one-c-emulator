package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.EmployeeModel;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeModel getById(UUID id) throws NotFoundException;

    List<EmployeeModel> list();

    EmployeeModel create(EmployeeModel model) throws ValidationException;

    List<EmployeeModel> create(final List<EmployeeModel> models) throws ValidationException;

    EmployeeModel update(EmployeeModel model) throws ValidationException, NotFoundException;

    void delete(UUID id);
}
