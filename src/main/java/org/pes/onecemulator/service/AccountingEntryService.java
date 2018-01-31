package org.pes.onecemulator.service;

import org.pes.onecemulator.dto.AccountingEntryDto;

import java.util.List;
import java.util.UUID;

public interface AccountingEntryService {

    AccountingEntryDto getById(UUID id) throws Exception;

    List<AccountingEntryDto> list();

    AccountingEntryDto create(AccountingEntryDto dto) throws Exception;

    AccountingEntryDto update(AccountingEntryDto dto) throws Exception;

    void delete(UUID id);
}
