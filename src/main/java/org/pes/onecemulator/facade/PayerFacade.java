package org.pes.onecemulator.facade;

import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.PayerModel;

import java.util.List;
import java.util.UUID;

public interface PayerFacade {

    List<PayerModel> list();

    List<String> listCodeBySource(String source);

    PayerModel create(PayerModel model) throws ValidationException;

    PayerModel update(PayerModel model) throws NotFoundException, ValidationException;

    void delete(UUID id);
}
