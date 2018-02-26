package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.ExpenseRequestModel;
import org.pes.onecemulator.service.ExpenseRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/expense-request")
public class ExpenseRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseRequestController.class);

    @Autowired
    private ExpenseRequestService expenseRequestService;

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
