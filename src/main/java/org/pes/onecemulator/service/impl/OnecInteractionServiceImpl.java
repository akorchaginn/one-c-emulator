package org.pes.onecemulator.service.impl;

import org.pes.onecemulator.service.OnecInteractionService;
import org.pes.onecemulator.service.OnecRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OnecInteractionServiceImpl implements OnecInteractionService {

    private final OnecRestClientService onecRestClientService;

    @Autowired
    public OnecInteractionServiceImpl(final OnecRestClientService onecRestClientService) {
        this.onecRestClientService = onecRestClientService;
    }

    @Override
    public void loadPayers(final String source) throws IOException, InterruptedException {
        onecRestClientService.getPayers(source);
    }

    @Override
    public void loadEmployees(final String source) throws IOException, InterruptedException {
        onecRestClientService.getEmployees(source);
    }
}
