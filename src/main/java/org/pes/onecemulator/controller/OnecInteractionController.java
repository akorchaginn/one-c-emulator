package org.pes.onecemulator.controller;

import org.pes.onecemulator.service.OnecInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/one-c")
public class OnecInteractionController {

    private final OnecInteractionService onecInteractionService;

    @Autowired
    public OnecInteractionController(final OnecInteractionService onecInteractionService) {
        this.onecInteractionService = onecInteractionService;
    }

    @GetMapping(value = "/load/payers/{source}")
    public void loadPayers(@PathVariable(value = "source") final String source) throws IOException, InterruptedException {
        onecInteractionService.loadPayers(source);
    }

    @GetMapping(value = "/load/employees/{source}")
    public void loadEmployees(@PathVariable(value = "source") final String source) throws IOException, InterruptedException {
        onecInteractionService.loadEmployees(source);
    }
}
