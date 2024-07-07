package com.bmi.internship.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmi.internship.example.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long>{
    
}
