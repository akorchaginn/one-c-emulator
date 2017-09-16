package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.AccountingEntryDto;
import org.pes.onecemulator.service.api.AccountingEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/entry")
public class EntryController {

    @Autowired
    private AccountingEntryService accountingEntryService;

    @RequestMapping(method = RequestMethod.GET, path = "/getbyid/{id}")
    public @ResponseBody ResponseEntity<AccountingEntryDto> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.getAccountingEntryById(UUID.fromString(id)),
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
    public @ResponseBody ResponseEntity<List<AccountingEntryDto>> list() {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.listAccountingEntry(),
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
    public @ResponseBody ResponseEntity<AccountingEntryDto> create(@RequestBody AccountingEntryDto accountingEntryDto) {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.createAccountingEntry(accountingEntryDto),
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
    public @ResponseBody ResponseEntity<AccountingEntryDto> update(@RequestBody AccountingEntryDto accountingEntryDto) throws Exception {
        try {
            return new ResponseEntity<>(
                    accountingEntryService.updateAccountingEntry(accountingEntryDto),
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
    /*
    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        try {
            accountingEntryRepositoryService.delete(UUID.fromString(id));
        } catch (Exception e) {
            return false;
        }

        return true;
    }*/
}
