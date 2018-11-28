package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.EmployeeModel;
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

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody EmployeeModel getById(@PathVariable UUID id) throws NotFoundException {
        return employeeService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<EmployeeModel> list() {
        return employeeService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody
    List<EmployeeModel> create(@RequestBody List<EmployeeModel> modelList) throws Exception {
        return employeeService.create(modelList);
    }

    @PostMapping(value = "/update")
    public @ResponseBody EmployeeModel update(@RequestBody EmployeeModel model) throws Exception {
        return employeeService.update(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") UUID id) {
        employeeService.delete(id);
    }
}
