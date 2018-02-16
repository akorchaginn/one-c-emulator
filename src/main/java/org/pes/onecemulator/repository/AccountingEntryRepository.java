package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.AccountingEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, UUID> {
}
