package org.example.testtaskbotscrew.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.testtaskbotscrew.entity.Department;
import org.example.testtaskbotscrew.entity.Lector;
import org.example.testtaskbotscrew.entity.Accreditation;
import org.example.testtaskbotscrew.repository.DepartmentRepository;
import org.example.testtaskbotscrew.repository.LectorRepository;
import org.example.testtaskbotscrew.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public Lector getHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(() -> new IllegalArgumentException("Department not found"));
        return department.getHead();
    }

    @Override
    public String getDepartmentStatistics(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(() -> new IllegalArgumentException("Department not found"));
        List<Lector> lectors = lectorRepository.findAllByDepartment(department);
        long assistantCount = lectors.stream().filter(lector -> lector.getDegree() == Accreditation.ASSISTANT).count();
        long associateProfessorCount = lectors.stream().filter(lector -> lector.getDegree() == Accreditation.ASSOCIATE_PROFESSOR).count();
        long professorCount = lectors.stream().filter(lector -> lector.getDegree() == Accreditation.PROFESSOR).count();
        return "assistans - " + assistantCount + "\n" +
                "associate professors - " + associateProfessorCount + "\n" +
                "professors - " + professorCount;
    }

    @Override
    public String getAverageSalary(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(() -> new IllegalArgumentException("Department not found"));
        List<Lector> lectors = lectorRepository.findAllByDepartment(department);
        double averageSalary = lectors.stream().mapToDouble(Lector::getSalary).average().orElse(0);
        return String.format("%.2f", averageSalary);

    }

    @Override
    public Long getEmployeeCount(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(() -> new IllegalArgumentException("Department not found"));
        List<Lector> lectors = lectorRepository.findAllByDepartment(department);
        return (long) lectors.size();
    }

    @Override
    public List<String> globalSearch(String template) {
        return lectorRepository.findAll().stream()
                .filter(lector -> lector.getName().contains(template) || lector.getSurname().contains(template))
                .map(lector -> lector.getName() + " " + lector.getSurname())
                .toList();
    }
}
