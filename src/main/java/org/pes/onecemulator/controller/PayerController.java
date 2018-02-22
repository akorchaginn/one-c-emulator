package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
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

@RestController
@RequestMapping("api/payer")
public class PayerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayerController.class);

    @Autowired
    private PayerService payerService;

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody PayerModel getById(@PathVariable UUID id) throws NotFoundException {
        return payerService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<PayerModel> list() {
        return payerService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody List<PayerModel> create(@RequestBody List<PayerModel> modelList) throws Exception {
        for (PayerModel model: modelList) {
            payerService.create(model);
        }
        return payerService.list();
    }

    @PostMapping(value = "/update")
    public @ResponseBody PayerModel update(@RequestBody PayerModel model) throws Exception {
        return payerService.update(model);
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") UUID id) {
        payerService.delete(id);
    }
}
