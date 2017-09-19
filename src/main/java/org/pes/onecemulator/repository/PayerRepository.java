package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Payer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PayerRepository extends JpaRepository<Payer, UUID> {
    @Query("select p from Payer p where p.code = ?1")
    Payer findByCode(String code);
}
