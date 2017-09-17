package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ExpenseRequestRepository extends JpaRepository<ExpenseRequest, UUID> {
    @Query("select e from ExpenseRequest e where e.number = ?1")
    ExpenseRequest findByNumber(String number);

    @Query("select count(e) > 0 from ExpenseRequest e where e.number = ?1")
    Boolean exists(String number);
}
