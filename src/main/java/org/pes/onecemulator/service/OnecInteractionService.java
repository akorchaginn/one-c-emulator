package org.pes.onecemulator.service;

import java.io.IOException;

public interface OnecInteractionService {

    void loadPayers(String source) throws IOException, InterruptedException;

    void loadEmployees(String source) throws IOException, InterruptedException;
}
