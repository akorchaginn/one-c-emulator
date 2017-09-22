package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.service.api.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/expense-request")
public class ExpenseRequestController {

    @Autowired
    private ExpenseRequestService expenseRequestService;

    @RequestMapping(method = RequestMethod.GET, path = "/getbyid/{id}")
    public @ResponseBody ResponseEntity<ExpenseRequestDto> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    expenseRequestService.getExpenseRequestById(UUID.fromString(id)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public @ResponseBody ResponseEntity<List<ExpenseRequestDto>> list() {
        try {
            return new ResponseEntity<>(
                    expenseRequestService.listExpenseRequest(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public @ResponseBody ResponseEntity<ExpenseRequestDto> create(@RequestBody ExpenseRequestDto expenseRequestDto) {
        try {
            return new ResponseEntity<>(
                    expenseRequestService.createExpenseRequest(expenseRequestDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public @ResponseBody ResponseEntity<ExpenseRequestDto> update(@RequestBody ExpenseRequestDto expenseRequestDto) {
        try {
            return new ResponseEntity<>(
                    expenseRequestService.updateExpenseRequest(expenseRequestDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public @ResponseBody ResponseEntity<ExpenseRequestDto> delete(@RequestBody ExpenseRequestDto expenseRequestDto) {
        try {
            return new ResponseEntity<>(
                    expenseRequestService.deleteExpenseRequest(expenseRequestDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
