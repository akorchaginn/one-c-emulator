package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.EmployeeModel;
import org.pes.onecemulator.repository.EmployeeRepository;
import org.pes.onecemulator.service.EmployeeService;
import org.pes.onecemulator.service.EmployeeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeSourceService employeeSourceService;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository,
                               final EmployeeSourceService employeeSourceService) {
        this.employeeRepository = employeeRepository;
        this.employeeSourceService = employeeSourceService;
    }

    @Override
    public Employee getById(final UUID id) throws NotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Employee.class, id));
    }

    @Override
    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public List<Employee> create(final List<EmployeeModel> models) throws ValidationException {
        final List<Employee> result = new ArrayList<>();
        for (EmployeeModel model : models) {
            validateModel(model);
            result.add(updateOrCreate(model, new Employee()));
        }

        return result;
    }

    @Transactional
    @Override
    public Employee update(final EmployeeModel model) throws ValidationException, NotFoundException {
        validateModel(model);

        if (model.getId() == null) {
            throw new ValidationException("Id is null.");
        }

        final Employee employee = employeeRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Employee.class, model.getId()));

        return updateOrCreate(model, employee);
    }

    @Override
    public void delete(final UUID id) {
        employeeRepository.deleteById(id);
    }

    private Employee updateOrCreate(final EmployeeModel model, final Employee employee) throws ValidationException {
        employee.setExternalId(model.getExternalId());
        employee.setFullName(model.getFullName());
        employee.setGender(model.getGender());
        employee.setBirthday(model.getBirthday());
        employee.setFizId(model.getFizId());
        employee.setPosition(model.getPosition());
        employee.setUnit(model.getUnit());
        employee.setPeriod(model.getPeriod());

        employeeSourceService.add(employee, model.getEmployeeSources());

        return employeeRepository.save(employee);
    }

    private void validateModel(final EmployeeModel model) throws ValidationException {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getEmployeeSources() == null) {
            throw new ValidationException("Employee source list is null.");
        }

        if (model.getEmployeeSources().isEmpty()) {
            throw new ValidationException("Employee source list is empty.");
        }
    }
}
