package org.example.testtaskbotscrew.service;

import org.example.testtaskbotscrew.entity.Lector;

import java.util.List;

public interface UniversityService {
    Lector getHeadOfDepartment(String departmentName);
    String getDepartmentStatistics(String departmentName);
    String getAverageSalary(String departmentName);
    Long getEmployeeCount(String departmentName);
    List<String> globalSearch(String template);
}
