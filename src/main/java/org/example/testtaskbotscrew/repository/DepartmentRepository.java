package org.example.testtaskbotscrew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.testtaskbotscrew.entity.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}

