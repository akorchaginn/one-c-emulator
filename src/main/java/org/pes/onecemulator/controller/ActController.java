package org.pes.onecemulator.controller;

import org.pes.onecemulator.converter.internal.ActModelConverter;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.ActModel;
import org.pes.onecemulator.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/act")
public class ActController {

    private static final ActModelConverter ACT_MODEL_CONVERTER = new ActModelConverter();

    private final ActService actService;

    @Autowired
    public ActController(ActService actService) {
        this.actService = actService;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public @ResponseBody
    ActModel getById(@PathVariable final UUID id) throws NotFoundException {
        return ACT_MODEL_CONVERTER.convert(actService.getById(id));
    }

    @GetMapping(value = "/list")
    public @ResponseBody
    List<ActModel> list() {
        return actService.list().stream()
                .map(ACT_MODEL_CONVERTER::convert)
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/create")
    public @ResponseBody ActModel create(@RequestBody final ActModel model) throws NotFoundException, ValidationException {
        return ACT_MODEL_CONVERTER.convert(actService.create(model));
    }

    @PostMapping(value = "/update")
    public @ResponseBody ActModel update(@RequestBody final ActModel model) throws NotFoundException, ValidationException {
        return ACT_MODEL_CONVERTER.convert(actService.update(model));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable final UUID id) {
        actService.delete(id);
    }
}
