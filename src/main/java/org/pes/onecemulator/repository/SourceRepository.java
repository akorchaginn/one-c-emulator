package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface SourceRepository extends JpaRepository<Source, UUID> {

    @Query("select s from Source s where s.name = :name")
    Optional<Source> findByName(@Param(value = "name") String name);
}
