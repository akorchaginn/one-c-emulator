package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    @Query("select i from Invoice i where i.id = :id and i.source.name = :source")
    Optional<Invoice> findByIdAndSource(
            @Param(value = "id") UUID id,
            @Param(value = "source") String source);

    @Query("select i from Invoice i where i.externalId = :externalId and i.source.name = :source")
    Optional<Invoice> findByExternalIdAndSource(
            @Param(value = "externalId") String externalId,
            @Param(value = "source") String source);
}
