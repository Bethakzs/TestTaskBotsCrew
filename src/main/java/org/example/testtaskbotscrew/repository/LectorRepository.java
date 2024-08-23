package org.example.testtaskbotscrew.repository;

import org.example.testtaskbotscrew.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.testtaskbotscrew.entity.Lector;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findAllByDepartment(Department department);
}


