package com.bmi.internship.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmi.internship.example.model.UnitDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.service.CRUDUnitService;

@RestController
@RequestMapping("/example/unit")
public class UnitController {
    @Autowired
    CRUDUnitService crudUnitService;

    @PostMapping("/create")
    public GlobalResponse createUnit(@RequestBody UnitDTO body) {
        return crudUnitService.createUnit(body);
    }

    @GetMapping("/view")
    public GlobalResponse viewUnits() {
        return crudUnitService.viewUnits();
    }

    @PostMapping("/update/{id}")
    public GlobalResponse updateUnit(@PathVariable Long id, @RequestBody UnitDTO body) {
        return crudUnitService.updateUnit(id, body);
    }

    @DeleteMapping("/delete/{id}")
    public GlobalResponse deleteUnit(@PathVariable Long id) {
        return crudUnitService.deleteUnit(id);
    }
}
