package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.AccountingEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AccountingEntryRepository extends JpaRepository<AccountingEntry, UUID> {
    @Query("select count(a) > 0 from AccountingEntry a where a.code = ?1")
    Boolean exists(String code);
}
