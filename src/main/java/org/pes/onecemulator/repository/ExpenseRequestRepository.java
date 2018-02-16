package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ExpenseRequestRepository extends JpaRepository<ExpenseRequest, UUID> {

    @Query("select e from ExpenseRequest e where e.number = :number")
    ExpenseRequest findByNumber(@Param(value = "number") String number);
}
