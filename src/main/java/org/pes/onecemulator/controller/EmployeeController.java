package org.pes.onecemulator.controller;

import org.pes.onecemulator.converter.internal.EmployeeModelConverter;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.internal.EmployeeModel;
import org.pes.onecemulator.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private static final EmployeeModelConverter EMPLOYEE_MODEL_CONVERTER = new EmployeeModelConverter();

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody EmployeeModel getById(@PathVariable final UUID id) throws NotFoundException {
        return EMPLOYEE_MODEL_CONVERTER.convert(employeeService.getById(id));
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<EmployeeModel> list() {
        return employeeService.list().stream()
                .map(EMPLOYEE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    public @ResponseBody List<EmployeeModel> create(@RequestBody final List<EmployeeModel> modelList) throws Exception {
        return employeeService.create(modelList).stream()
                .map(EMPLOYEE_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/update")
    public @ResponseBody EmployeeModel update(@RequestBody final EmployeeModel model) throws Exception {
        return EMPLOYEE_MODEL_CONVERTER.convert(employeeService.update(model));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") final UUID id) {
        employeeService.delete(id);
    }
}
