package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.ExpenseRequestDto;
import org.pes.onecemulator.service.api.ExpenseRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("warning:", e.getMessage());
            return new ResponseEntity<>(
                    responseHeaders,
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
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("warning:", e.getMessage());
            return new ResponseEntity<>(
                    responseHeaders,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public @ResponseBody ResponseEntity<ExpenseRequestDto> create(@RequestBody ExpenseRequestDto accountingEntryDto) {
        try {
            return new ResponseEntity<>(
                    expenseRequestService.createExpenseRequest(accountingEntryDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("warning:", e.getMessage());
            return new ResponseEntity<>(
                    responseHeaders,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public @ResponseBody ResponseEntity<ExpenseRequestDto> update(@RequestBody ExpenseRequestDto accountingEntryDto) {
        try {
            return new ResponseEntity<>(
                    expenseRequestService.updateExpenseRequest(accountingEntryDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("warning:", e.getMessage());
            return new ResponseEntity<>(
                    responseHeaders,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
