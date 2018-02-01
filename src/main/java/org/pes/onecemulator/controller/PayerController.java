package org.pes.onecemulator.controller;

import org.pes.onecemulator.model.PayerModel;
import org.pes.onecemulator.service.PayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/payer")
public class PayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayerController.class);

    private static final Object OK = "OK";

    @Autowired
    private PayerService payerService;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody PayerModel getById(@PathVariable UUID id) {
        return payerService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<PayerModel> list() {
        return payerService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody PayerModel create(@RequestBody PayerModel model) {
        return payerService.create(model);
    }

    @PostMapping(value = "/create-all")
    public @ResponseBody List<PayerModel> create(@RequestBody List<PayerModel> modelList) {
        return modelList.stream().map(p -> payerService.create(p)).collect(Collectors.toList());
    }

    @PostMapping(value = "/update")
    public @ResponseBody PayerModel update(@RequestBody PayerModel model) {
        return payerService.update(model);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") UUID id) {
        payerService.delete(id);
    }
}
