package org.pes.onecemulator.service;

import org.pes.onecemulator.dto.InvoiceDto;

import java.util.List;
import java.util.UUID;

public interface InvoiceService {

    InvoiceDto getIById(UUID id) throws Exception;

    InvoiceDto getByIdAndSource(UUID id, String source) throws Exception;

    InvoiceDto getByExternalId(String externalId) throws Exception;

    InvoiceDto getByExternalIdAndSource(String externalId, String source) throws Exception;

    List<InvoiceDto> list();

    InvoiceDto create(InvoiceDto invoiceDto) throws Exception;

    InvoiceDto update(InvoiceDto invoiceDto) throws Exception;

    void delete(UUID id);
}
