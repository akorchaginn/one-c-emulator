package org.pes.onecemulator.service;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.EmployeeModel;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeModel getById(UUID id) throws NotFoundException;

    List<EmployeeModel> list();

    EmployeeModel create(EmployeeModel model) throws Exception;

    EmployeeModel update(EmployeeModel model) throws Exception;

    void delete(UUID id);
}
