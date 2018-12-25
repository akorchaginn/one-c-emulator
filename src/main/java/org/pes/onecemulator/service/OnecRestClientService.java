package org.pes.onecemulator.service;

import org.pes.onecemulator.model.EmployeeCrm;
import org.pes.onecemulator.model.PayerCrm;

import java.io.IOException;
import java.util.List;

public interface OnecRestClientService {

    List<PayerCrm> getPayers(final String source) throws IOException, InterruptedException;

    List<EmployeeCrm> getEmployees(final String source) throws IOException, InterruptedException;
}
