package com.bmi.internship.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmi.internship.example.entity.Unit;
import java.util.Optional;

@Repository
public interface UnitRepo extends JpaRepository<Unit, Long> {
    Optional<Unit> findByAbbreviation(String abbreviation);
}
