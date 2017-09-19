package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.InvoiceDto;
import org.pes.onecemulator.service.api.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(method = RequestMethod.GET, path = "/getbyid/{id}")
    public @ResponseBody
    ResponseEntity<InvoiceDto> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    invoiceService.getInvoiceById(UUID.fromString(id)),
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
    public @ResponseBody ResponseEntity<List<InvoiceDto>> list() {
        try {
            return new ResponseEntity<>(
                    invoiceService.listInvoice(),
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
    public @ResponseBody ResponseEntity<InvoiceDto> create(@RequestBody InvoiceDto accountingEntryDto) {
        try {
            return new ResponseEntity<>(
                    invoiceService.createInvoice(accountingEntryDto),
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
    public @ResponseBody ResponseEntity<InvoiceDto> update(@RequestBody InvoiceDto accountingEntryDto) throws Exception {
        try {
            return new ResponseEntity<>(
                    invoiceService.updateInvoice(accountingEntryDto),
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

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public ResponseEntity<InvoiceDto> delete(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    invoiceService.deleteInvoice(UUID.fromString(id)),
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
