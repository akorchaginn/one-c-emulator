package org.pes.onecemulator.service;

import org.pes.onecemulator.model.onec.EmployeeModel;
import org.pes.onecemulator.model.onec.PayerModel;

import java.io.IOException;
import java.util.List;

public interface OnecRestClientService {

    List<PayerModel> getPayers(final String source) throws IOException, InterruptedException;

    List<EmployeeModel> getEmployees(final String source) throws IOException, InterruptedException;
}
