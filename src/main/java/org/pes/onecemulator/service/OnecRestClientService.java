package org.pes.onecemulator.service;

import java.io.IOException;

public interface OnecRestClientService {

    void getPayers(String source) throws IOException, InterruptedException;

    void getEmployees(String source) throws IOException, InterruptedException;
}
