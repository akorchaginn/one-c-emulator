package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.PayerDto;
import org.pes.onecemulator.service.api.PayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/payer")
public class PayerController {

    @Autowired
    private PayerService payerService;

    @RequestMapping(method = RequestMethod.GET, path = "/getbyid/{id}")
    public @ResponseBody
    ResponseEntity<PayerDto> getById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(
                    payerService.getPayerById(UUID.fromString(id)),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public @ResponseBody ResponseEntity<List<PayerDto>> list() {
        try {
            return new ResponseEntity<>(
                    payerService.listPayer(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public @ResponseBody ResponseEntity<PayerDto> create(@RequestBody PayerDto payerDto) {
        try {
            return new ResponseEntity<>(
                    payerService.createPayer(payerDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create-all")
    public @ResponseBody ResponseEntity<List<PayerDto>> create(@RequestBody List<PayerDto> payerDtos) {
        try {
            return new ResponseEntity<>(
                    payerService.createPayer(payerDtos),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public @ResponseBody ResponseEntity<PayerDto> update(@RequestBody PayerDto payerDto) {
        try {
            return new ResponseEntity<>(
                    payerService.updatePayer(payerDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delete")
    public @ResponseBody ResponseEntity<PayerDto> delete(@RequestBody PayerDto payerDto) {
        try {
            return new ResponseEntity<>(
                    payerService.deletePayer(payerDto),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
