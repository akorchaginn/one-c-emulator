package org.pes.onecemulator.service;

import org.pes.onecemulator.model.PayerModel;

import java.util.List;
import java.util.UUID;

public interface PayerService {

    PayerModel getById(UUID id);

    PayerModel getByCode(String code);

    List<PayerModel> list();

    PayerModel create(PayerModel model);

    PayerModel update(PayerModel model);

    void delete(UUID id);
}
