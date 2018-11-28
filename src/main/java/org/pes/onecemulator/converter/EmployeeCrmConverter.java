package org.pes.onecemulator.converter;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.entity.EmployeeSource;
import org.pes.onecemulator.model.EmployeeCrm;

import java.time.LocalDate;

public class EmployeeCrmConverter implements Converter<Employee, EmployeeCrm> {

    private final String source;

    public EmployeeCrmConverter(final String source) {
        this.source = source;
    }

    @Override
    public EmployeeCrm convert(final Employee entity) {
        final EmployeeCrm model = new EmployeeCrm();
        model.setExternalId(entity.getExternalId());
        model.setFullName(entity.getFullName());
        model.setGender(entity.getGender());
        model.setBirthday(entity.getBirthday());
        model.setStartDate(getStartDate(entity));
        model.setEndDate(getEndDate(entity));
        model.setFizId(entity.getFizId());
        model.setPosition(entity.getPosition());
        model.setUnit(entity.getUnit());
        model.setPeriod(entity.getPeriod());

        return model;
    }

    private LocalDate getStartDate(final Employee entity) {
        return entity.getEmployeeSources().stream()
                .filter(es -> es.getSource().getName().equals(source))
                .map(EmployeeSource::getStartDate)
                .findFirst()
                .orElse(null);
    }

    private LocalDate getEndDate(final Employee entity) {
        return entity.getEmployeeSources().stream()
                .filter(es -> es.getSource().getName().equals(source))
                .map(EmployeeSource::getEndDate)
                .findFirst()
                .orElse(null);
    }
}
