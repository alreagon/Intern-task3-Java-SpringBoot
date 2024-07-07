package com.bmi.internship.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmi.internship.example.model.EmployeeDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.service.CRUDEmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/example/employee")
public class MainController {
    @Autowired
    CRUDEmployeeService crudEmployeeService;

    @PostMapping("/create")
    public GlobalResponse tambahPegawai(@RequestBody EmployeeDTO body) {
        GlobalResponse response = new GlobalResponse();
        try {
            response = crudEmployeeService.createEmployee(body);
        } catch (Exception e) {
            response.setStatus("malfunction");
            response.setDescription("something went wrong");
            response.setDetails(e.getMessage());
        }
        
        return response;
    }

    @PostMapping("/view")
    public GlobalResponse lihatPegawai() {
        GlobalResponse response = new GlobalResponse();
        try {
            response = crudEmployeeService.viewEmployee();
        } catch (Exception e) {
            response.setStatus("malfunction");
            response.setDescription("something went wrong");
            response.setDetails(e.getMessage());

        }
        
        return response;
    }
    
}
