package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.exception.ValidationException;

import java.util.Set;

public interface PayerSourceService {

    void add(Payer payer, Set<String> sources) throws ValidationException;
}
