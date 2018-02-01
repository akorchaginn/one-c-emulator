package org.pes.onecemulator.controller;

import org.pes.onecemulator.model.ERequestModel;
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

    private static final Object OK = "OK";

    @Autowired
    private ExpenseRequestService expenseRequestService;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody ERequestModel getById(@PathVariable UUID id) {
        return expenseRequestService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<ERequestModel> list() {
        return expenseRequestService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody ERequestModel create(@RequestBody ERequestModel model) {
        return expenseRequestService.create(model);
    }

    @PostMapping(value = "/update")
    public @ResponseBody ERequestModel update(@RequestBody ERequestModel model) {
        return expenseRequestService.update(model);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") UUID id) {
        expenseRequestService.delete(id);
    }
}
