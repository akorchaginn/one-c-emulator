package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query("select ee from Employee ee where ee.externalId = :externalId")
    Optional<Employee> findByExternalId(@Param(value = "externalId") String externalId);
}
