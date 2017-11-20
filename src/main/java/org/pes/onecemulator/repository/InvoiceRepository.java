package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    @Query("select i from Invoice i where i.externalId = ?1")
    Invoice findByExternalId(UUID uuid);
}
