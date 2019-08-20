package org.pes.onecemulator.controller;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.model.internal.PayerModel;
import org.pes.onecemulator.service.PayerService;
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

@RestController
@RequestMapping("api/payer")
public class PayerController {

    private final PayerService payerService;

    @Autowired
    public PayerController(final PayerService payerService) {
        this.payerService = payerService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody PayerModel getById(@PathVariable final UUID id) throws NotFoundException {
        return payerService.getById(id);
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<PayerModel> list() {
        return payerService.list();
    }

    @PostMapping(value = "/create")
    public @ResponseBody List<PayerModel> create(@RequestBody final List<PayerModel> modelList) throws Exception {
        return payerService.create(modelList);
    }

    @PostMapping(value = "/update")
    public @ResponseBody PayerModel update(@RequestBody final PayerModel model) throws Exception {
        return payerService.update(model);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") final UUID id) {
        payerService.delete(id);
    }
}
