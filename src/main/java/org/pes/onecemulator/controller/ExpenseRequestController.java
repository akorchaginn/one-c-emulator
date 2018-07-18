package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.service.ExpenseRequestService;
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
@RequestMapping("api/expense-request")
public class ExpenseRequestController {

    private final ExpenseRequestService expenseRequestService;

    @Autowired
    public ExpenseRequestController(ExpenseRequestService expenseRequestService) {
        this.expenseRequestService = expenseRequestService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody
    ExpenseRequestModel getById(@PathVariable UUID id) throws NotFoundException {
        return expenseRequestService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<ExpenseRequestModel> list() {
        return expenseRequestService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody
    ExpenseRequestModel create(@RequestBody ExpenseRequestModel model) throws Exception {
        return expenseRequestService.create(model);
    }

    @PostMapping(value = "/update")
    public @ResponseBody
    ExpenseRequestModel update(@RequestBody ExpenseRequestModel model) throws Exception {
        return expenseRequestService.update(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") UUID id) {
        expenseRequestService.delete(id);
    }
}
