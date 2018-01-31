package org.pes.onecemulator.service;

import org.pes.onecemulator.dto.PayerDto;

import java.util.List;
import java.util.UUID;

public interface PayerService {

    PayerDto getById(UUID id) throws Exception;

    PayerDto getByCode(String code) throws Exception;

    List<PayerDto> list();

    PayerDto create(PayerDto payerDto) throws Exception;

    PayerDto update(PayerDto payerDto) throws Exception;

    void delete(UUID id);
}
