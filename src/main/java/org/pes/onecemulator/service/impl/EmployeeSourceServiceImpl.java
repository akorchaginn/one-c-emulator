package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.entity.EmployeeSource;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.EmployeeSourceModel;
import org.pes.onecemulator.repository.EmployeeSourceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.EmployeeSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class EmployeeSourceServiceImpl implements EmployeeSourceService {

    private final EmployeeSourceRepository employeeSourceRepository;

    private final SourceRepository sourceRepository;

    @Autowired
    public EmployeeSourceServiceImpl(final EmployeeSourceRepository employeeSourceRepository,
                                     final SourceRepository sourceRepository) {
        this.employeeSourceRepository = employeeSourceRepository;
        this.sourceRepository = sourceRepository;
    }

    @Transactional
    @Override
    public void add(final Employee employee, final Set<EmployeeSourceModel> sourceModels) throws ValidationException {
        if (employee == null) {
            throw new ValidationException("Employee is null.");
        }

        if (sourceModels == null || sourceModels.isEmpty()) {
            throw new ValidationException("EmployeeSourceModel list is null or emplty.");
        }

        for (EmployeeSourceModel model : sourceModels) {
            final Source source = sourceRepository.findByName(model.getSource())
                    .orElseGet(() -> {
                        final Source newSource = new Source();
                        newSource.setName(model.getSource());
                        return newSource;
                    });

            final EmployeeSource employeeSource = employeeSourceRepository.findById(model.getId())
                    .orElseGet(EmployeeSource::new);

            updateOrCreate(model, employeeSource, employee, source);
        }
    }

    private void updateOrCreate(final EmployeeSourceModel model,
                                          final EmployeeSource employeeSource,
                                          final Employee employee,
                                          final Source source) {
        employeeSource.setEmployee(employee);
        employeeSource.setSource(source);
        employeeSource.setStartDate(model.getStartDate());
        employeeSource.setEndDate(model.getEndDate());

        employeeSourceRepository.save(employeeSource);
    }
}
