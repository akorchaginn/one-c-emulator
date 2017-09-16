package org.pes.onecemulator.service.repository;

import org.pes.onecemulator.entity.Payer;

import java.util.List;
import java.util.UUID;

public interface PayerRepositoryService {
    Payer findById(UUID id);
    List<Payer> findAll();
    Payer create(Payer payer) throws Exception;
    Payer update(Payer payer) throws Exception;
    void delete(UUID id) throws Exception;
}
