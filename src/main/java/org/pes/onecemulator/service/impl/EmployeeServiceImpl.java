package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.EmployeeModel;
import org.pes.onecemulator.repository.EmployeeRepository;
import org.pes.onecemulator.service.EmployeeService;
import org.pes.onecemulator.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final SourceService sourceService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SourceService sourceService) {
        this.employeeRepository = employeeRepository;
        this.sourceService = sourceService;
    }

    @Override
    public EmployeeModel getById(UUID id) throws NotFoundException {
        return employeeRepository.findById(id)
                .map(this::getModel)
                .orElseThrow(() -> new NotFoundException(Employee.class, id));
    }

    @Override
    public List<EmployeeModel> list() {
        return employeeRepository.findAll()
                .stream()
                .map(this::getModel)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeModel create(EmployeeModel model) throws Exception {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getSources() == null) {
            throw new ValidationException("Sources list is null.");
        }

        if (model.getSources().isEmpty()) {
            throw new ValidationException("Source list is empty.");
        }

        final Set<Source> newSources = sourceService.updateOrCreate(model.getSources());

        if (newSources.isEmpty()) {
            throw new ValidationException("New source list is empty.");
        }

        Employee employee = new Employee();
        employee.setExternalId(model.getExternalId());
        employee.setFullName(model.getFullName());
        employee.setGender(model.getGender());
        employee.setBirthday(model.getBirthday());
        employee.setStartDate(model.getStartDate());
        employee.setEndDate(model.getEndDate());
        employee.setFizId(model.getFizId());
        employee.setPosition(model.getPosition());
        employee.setUnit(model.getUnit());
        employee.setPeriod(model.getPeriod());
        employee.setSources(newSources);
        employee = employeeRepository.save(employee);

        return getModel(employee);
    }

    @Override
    public EmployeeModel update(EmployeeModel model) throws Exception {
        if (model == null) {
            throw new ValidationException("Model is null.");
        }

        if (model.getSources() == null) {
            throw new ValidationException("Sources list is null.");
        }

        if (model.getSources().isEmpty()) {
            throw new ValidationException("Source list is empty.");
        }

        final Set<Source> newSources = sourceService.updateOrCreate(model.getSources());

        if (newSources.isEmpty()) {
            throw new ValidationException("New source list is empty.");
        }

        Employee employee = employeeRepository.findById(model.getId())
                .orElseThrow(() -> new NotFoundException(Employee.class, model.getId()));

        employee.setExternalId(model.getExternalId());
        employee.setFullName(model.getFullName());
        employee.setGender(model.getGender());
        employee.setBirthday(model.getBirthday());
        employee.setStartDate(model.getStartDate());
        employee.setEndDate(model.getEndDate());
        employee.setFizId(model.getFizId());
        employee.setPosition(model.getPosition());
        employee.setUnit(model.getUnit());
        employee.setPeriod(model.getPeriod());
        employee.setSources(newSources);
        employee = employeeRepository.save(employee);

        return getModel(employee);
    }

    @Override
    public void delete(UUID id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeModel getModel(final Employee entity) {
        final EmployeeModel model = new EmployeeModel();
        model.setId(entity.getId());
        model.setExternalId(entity.getExternalId());
        model.setFullName(entity.getFullName());
        model.setGender(entity.getGender());
        model.setBirthday(entity.getBirthday());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setFizId(entity.getFizId());
        model.setPosition(entity.getPosition());
        model.setUnit(entity.getUnit());
        model.setPeriod(entity.getPeriod());
        model.getSources().addAll(entity.getSources().stream()
                .map(Source::getName)
                .collect(Collectors.toSet()));

        return model;
    }
}
