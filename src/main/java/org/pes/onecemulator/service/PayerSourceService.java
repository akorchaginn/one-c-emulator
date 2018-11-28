package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.exception.ValidationException;

import java.util.Set;

public interface PayerSourceService {

    void add(final Payer payer, final Set<String> sources) throws ValidationException;
}
