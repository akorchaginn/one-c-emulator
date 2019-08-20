package org.pes.onecemulator.controller;

import org.pes.onecemulator.converter.internal.ExpenseRequestModelConverter;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.ExpenseRequestModel;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/expense-request")
public class ExpenseRequestController {

    private static final ExpenseRequestModelConverter EXPENSE_REQUEST_MODEL_CONVERTER = new ExpenseRequestModelConverter();

    private final ExpenseRequestService expenseRequestService;

    @Autowired
    public ExpenseRequestController(final ExpenseRequestService expenseRequestService) {
        this.expenseRequestService = expenseRequestService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody ExpenseRequestModel getById(@PathVariable final UUID id) throws NotFoundException {
        return EXPENSE_REQUEST_MODEL_CONVERTER.convert(expenseRequestService.getById(id));
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<ExpenseRequestModel> list() {
        return expenseRequestService.list().stream()
                .map(EXPENSE_REQUEST_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    public @ResponseBody ExpenseRequestModel create(@RequestBody final ExpenseRequestModel model) throws NotFoundException, ValidationException {
        return EXPENSE_REQUEST_MODEL_CONVERTER.convert(expenseRequestService.create(model));
    }

    @PostMapping(value = "/update")
    public @ResponseBody ExpenseRequestModel update(@RequestBody final ExpenseRequestModel model) throws NotFoundException, ValidationException {
        return EXPENSE_REQUEST_MODEL_CONVERTER.convert(expenseRequestService.update(model));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") final UUID id) {
        expenseRequestService.delete(id);
    }
}
