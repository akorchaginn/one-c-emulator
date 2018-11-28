package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.PayerSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PayerSourceRepository extends JpaRepository<PayerSource, UUID> {
}
