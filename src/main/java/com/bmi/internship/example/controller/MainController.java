package com.bmi.internship.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmi.internship.example.model.EmployeeDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.service.CRUDEmployeeService;

@RestController
@RequestMapping("/example/employee")
public class MainController {
    @Autowired
    CRUDEmployeeService crudEmployeeService;

    @PostMapping("/create")
    public GlobalResponse createEmployee(@RequestBody EmployeeDTO body) {
        return crudEmployeeService.createEmployee(body);
    }

    @GetMapping("/view")
    public GlobalResponse viewEmployees() {
        return crudEmployeeService.viewEmployees();
    }

    @PostMapping("/update/{id}")
    public GlobalResponse updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO body) {
        return crudEmployeeService.updateEmployee(id, body);
    }

    @DeleteMapping("/delete/{id}")
    public GlobalResponse deleteEmployee(@PathVariable Long id) {
        return crudEmployeeService.deleteEmployee(id);
    }
}
