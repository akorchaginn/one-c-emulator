package org.pes.onecemulator.repository;

import org.pes.onecemulator.entity.EmployeeSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeSourceRepository extends JpaRepository<EmployeeSource, UUID> {
}
