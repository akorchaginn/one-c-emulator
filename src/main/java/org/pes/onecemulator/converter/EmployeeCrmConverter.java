package org.pes.onecemulator.converter;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.model.EmployeeCrm;

public class EmployeeCrmConverter implements Converter<Employee, EmployeeCrm> {

    @Override
    public EmployeeCrm convert(final Employee entity) {
        final EmployeeCrm model = new EmployeeCrm();
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

        return model;
    }
}
