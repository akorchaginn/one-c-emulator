package org.pes.onecemulator.converter.onec;

import org.pes.onecemulator.converter.Converter;
import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.entity.EmployeeSource;
import org.pes.onecemulator.model.onec.EmployeeModel;

public class EmployeeModelConverter implements Converter<EmployeeSource, EmployeeModel> {

    @Override
    public EmployeeModel convert(final EmployeeSource employeeSource) {
        if (employeeSource == null) {
            return null;
        }
        final Employee employee = employeeSource.getEmployee();
        final EmployeeModel model = new EmployeeModel();
        model.setExternalId(employee.getExternalId());
        model.setFullName(employee.getFullName());
        model.setGender(employee.getGender());
        model.setBirthday(employee.getBirthday());
        model.setStartDate(employeeSource.getStartDate());
        model.setEndDate(employeeSource.getEndDate());
        model.setFizId(employee.getFizId());
        model.setPosition(employee.getPosition());
        model.setUnit(employee.getUnit());
        model.setPeriod(employee.getPeriod());

        return model;
    }

}
