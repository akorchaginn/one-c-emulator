package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.ExpenseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface ExpenseRequestRepository extends JpaRepository<ExpenseRequest, UUID> {

    @Query("select er from ExpenseRequest er where er.number = :number")
    Optional<ExpenseRequest> findByNumber(@Param(value = "number") String number);
}
