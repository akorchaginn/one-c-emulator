package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.List;
import java.util.UUID;

public interface PayerService {

    Payer getById(UUID id) throws NotFoundException;

    List<Payer> list();

    List<Payer> listBySource(String source);

    Payer create(PayerModel model) throws ValidationException;

    Payer update(PayerModel model) throws NotFoundException, ValidationException;

    void delete(UUID id);
}
