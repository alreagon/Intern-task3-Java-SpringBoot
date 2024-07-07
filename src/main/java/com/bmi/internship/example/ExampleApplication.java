package com.bmi.internship.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bmi.internship.example.entity.Unit;
import com.bmi.internship.example.repository.UnitRepo;

@SpringBootApplication
public class ExampleApplication implements CommandLineRunner {

    @Autowired
    private UnitRepo unitRepo;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        unitRepo.save(new Unit(1L, "DIB", "Digital Banking"));
        unitRepo.save(new Unit(2L, "ITY", "IT"));
    }
}
