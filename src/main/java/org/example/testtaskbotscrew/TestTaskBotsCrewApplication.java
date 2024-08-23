package org.example.testtaskbotscrew;

import lombok.RequiredArgsConstructor;
import org.example.testtaskbotscrew.service.UniversityService;
import org.example.testtaskbotscrew.service.impl.UniversityServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class TestTaskBotsCrewApplication implements CommandLineRunner {

    private final UniversityService universityService;

    public static void main(String[] args) {
        SpringApplication.run(TestTaskBotsCrewApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            } else if (input.startsWith("Who is head of department")) {
                String departmentName = input.substring("Who is head of department".length()).trim();
                if (departmentName.isEmpty()) {
                    System.out.println("Department name cannot be empty");
                } else {
                    universityService.getHeadOfDepartment(departmentName);
                    System.out.println("Head of department: " + departmentName + " department is " + universityService.getHeadOfDepartment(departmentName));
                }
            } else if (input.startsWith("Show") && input.contains("statistics")) {
                String departmentName = input.substring(input.indexOf("Show") + 4, input.indexOf("statistics")).trim();
                if (departmentName.isEmpty()) {
                    System.out.println("Department name cannot be empty");
                } else {
                    System.out.println(universityService.getDepartmentStatistics(departmentName));
                }
            } else if (input.startsWith("Show the average salary for the department")) {
                String departmentName = input.substring("Show the average salary for the department".length()).trim();
                if (departmentName.isEmpty()) {
                    System.out.println("Department name cannot be empty");
                } else {
                    System.out.println("The average salary of " + departmentName + " is " + universityService.getAverageSalary(departmentName));
                }
            } else if (input.startsWith("Show count of employee for")) {
                String departmentName = input.substring("Show count of employee for".length()).trim();
                if (departmentName.isEmpty()) {
                    System.out.println("Department name cannot be empty");
                } else {
                    System.out.println(universityService.getEmployeeCount(departmentName));
                }
            } else if (input.startsWith("Global search by")) {
                String template = input.substring("Global search by".length()).trim();
                if (template.isEmpty()) {
                    System.out.println("Search template cannot be empty");
                } else {
                    System.out.println(universityService.globalSearch(template));
                }
            } else {
                System.out.println("Unknown command");
            }
        }
        scanner.close();
    }
}
