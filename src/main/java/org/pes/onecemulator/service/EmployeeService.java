package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.EmployeeModel;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee getById(UUID id) throws NotFoundException;

    List<Employee> list();

    List<Employee> create(List<EmployeeModel> models) throws ValidationException;

    Employee update(EmployeeModel model) throws ValidationException, NotFoundException;

    void delete(UUID id);
}
