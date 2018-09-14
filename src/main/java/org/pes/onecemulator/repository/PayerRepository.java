package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Payer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface PayerRepository extends JpaRepository<Payer, UUID> {

    @Query("select p from Payer p where p.code = :code")
    Optional<Payer> findByCode(@Param(value = "code") String code);
}
