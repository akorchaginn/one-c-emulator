package org.pes.onecemulator.service.repository;

import org.pes.onecemulator.entity.Payer;

import java.util.List;
import java.util.UUID;

public interface PayerRepositoryService {
    Payer findById(UUID id);
    Payer findByCode(String code);
    List<Payer> findAll();
    Payer create(Payer payer);
    Payer update(Payer payer);
    Payer delete(UUID id);
}
