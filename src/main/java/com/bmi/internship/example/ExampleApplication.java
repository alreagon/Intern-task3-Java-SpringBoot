package com.bmi.internship.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bmi.internship.example.entity.Function;
import com.bmi.internship.example.entity.Unit;
import com.bmi.internship.example.repository.FunctionRepo;
import com.bmi.internship.example.repository.UnitRepo;

@SpringBootApplication
public class ExampleApplication implements CommandLineRunner {

    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private FunctionRepo functionRepo;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Unit unitDIB = new Unit("DIB", "Digital Banking");
        // Unit unitITY = new Unit("ITY", "IT");
        // unitRepo.save(unitDIB);
        // unitRepo.save(unitITY);

        // functionRepo.save(new Function("DIB1", "Digital System Development", unitDIB));
        // functionRepo.save(new Function("DIB2", "Digital Business Partnership", unitDIB));
        // functionRepo.save(new Function("DIB3", "Digital Product & Project Management", unitDIB));
        // functionRepo.save(new Function("ITY1", "Application Surrounding", unitITY));
        // functionRepo.save(new Function("ITY2", "Governance", unitITY));
        // functionRepo.save(new Function("ITY3", "Infrastructure", unitITY));
    }
}
