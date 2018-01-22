package org.pes.onecemulator.service.repository;

import org.pes.onecemulator.entity.Invoice;

import java.util.List;
import java.util.UUID;

public interface InvoiceRepositoryService {

    Invoice findById(UUID id);

    Invoice findByIdAndSource(UUID id, String source);

    Invoice findByExternalId(String externalId);

    Invoice findByExternalIdAndSource(String externalId, String source);

    List<Invoice> findAll();

    Invoice create(Invoice invoice) throws Exception;

    Invoice update(Invoice invoice) throws Exception;

    Invoice delete(UUID id) throws Exception;
}
