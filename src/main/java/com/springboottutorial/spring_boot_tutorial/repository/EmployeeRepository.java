package com.springboottutorial.spring_boot_tutorial.repository;

import com.springboottutorial.spring_boot_tutorial.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
