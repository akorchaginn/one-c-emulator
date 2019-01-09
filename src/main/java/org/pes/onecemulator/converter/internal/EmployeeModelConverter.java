package org.pes.onecemulator.converter.internal;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.model.internal.EmployeeModel;
import org.pes.onecemulator.model.internal.EmployeeSourceModel;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeModelConverter implements Converter<Employee, EmployeeModel> {

    @Override
    public EmployeeModel convert(final Employee entity) {
        final EmployeeModel model = new EmployeeModel();
        model.setId(entity.getId());
        model.setExternalId(entity.getExternalId());
        model.setFullName(entity.getFullName());
        model.setGender(entity.getGender());
        model.setBirthday(entity.getBirthday());
        model.setFizId(entity.getFizId());
        model.setPosition(entity.getPosition());
        model.setUnit(entity.getUnit());
        model.setPeriod(entity.getPeriod());
        model.getEmployeeSources().addAll(getEmployeeSources(entity));

        return model;
    }

    private List<EmployeeSourceModel> getEmployeeSources(final Employee entity) {
        return entity.getEmployeeSources().stream()
                .map(es -> {
                    final EmployeeSourceModel model = new EmployeeSourceModel();
                    model.setId(es.getId());
                    model.setStartDate(es.getStartDate());
                    model.setEndDate(es.getEndDate());
                    model.setSource(es.getSource().getName());
                    return model;
                })
                .collect(Collectors.toList());
    }
}
