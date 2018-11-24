package org.pes.onecemulator.converter;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.model.EmployeeModel;

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
