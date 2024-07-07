package com.bmi.internship.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmi.internship.example.entity.Function;

@Repository
public interface FunctionRepo extends JpaRepository<Function, String> {
}
