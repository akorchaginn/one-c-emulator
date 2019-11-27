package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, UUID> {
}
