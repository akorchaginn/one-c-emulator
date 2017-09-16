package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Payer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PayerRepository extends JpaRepository<Payer, UUID> {
}
