package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SourceRepository extends JpaRepository<Source, UUID> {

    @Query("select s from Source s where s.name = :name")
    Optional<Source> findByName(@Param(value = "name") String name);
}
