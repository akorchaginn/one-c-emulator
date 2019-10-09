package org.pes.onecemulator.controller;

import org.pes.onecemulator.converter.internal.PayerModelConverter;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/payer")
public class PayerController {

    private static final PayerModelConverter PAYER_MODEL_CONVERTER = new PayerModelConverter();

    private final PayerService payerService;

    @Autowired
    public PayerController(final PayerService payerService) {
        this.payerService = payerService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody PayerModel getById(@PathVariable final UUID id) throws NotFoundException {
        return PAYER_MODEL_CONVERTER.convert(payerService.getById(id));
    }

    @GetMapping(value = "/list")
    public @ResponseBody List<PayerModel> list() {
        return payerService.list().stream()
                .map(PAYER_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/list-by-source/{source}")
    public @ResponseBody List<PayerModel> listBySource(@PathVariable final String source) {
        return payerService.listBySource(source).stream()
                .map(PAYER_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    public @ResponseBody List<PayerModel> create(@RequestBody final List<PayerModel> modelList) throws ValidationException {
        final List<Payer> payers = new ArrayList<>();
        for (PayerModel payerModel : modelList) {
            payers.add(payerService.create(payerModel));
        }

        return payers.stream()
                .map(PAYER_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/update")
    public @ResponseBody List<PayerModel> update(@RequestBody final List<PayerModel> modelList) throws ValidationException, NotFoundException {
        final List<Payer> payers = new ArrayList<>();
        for (PayerModel payerModel : modelList) {
            payers.add(payerService.update(payerModel));
        }

        return payers.stream()
                .map(PAYER_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable(value = "id") final UUID id) {
        payerService.delete(id);
    }
}
