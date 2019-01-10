package org.pes.onecemulator.service;

import java.io.IOException;

public interface OnecInteractionService {

    void loadPayers(final String source) throws IOException, InterruptedException;

    void loadEmployees(final String source) throws IOException, InterruptedException;
}
