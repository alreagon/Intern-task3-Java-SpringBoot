package com.bmi.internship.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmi.internship.example.model.FunctionDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.service.CRUDFunctionService;

@RestController
@RequestMapping("/example/function")
public class FunctionController {
    @Autowired
    CRUDFunctionService crudFunctionService;

    @PostMapping("/create")
    public GlobalResponse createFunction(@RequestBody FunctionDTO body) {
        return crudFunctionService.createFunction(body);
    }

    @GetMapping("/view")
    public GlobalResponse viewFunctions() {
        return crudFunctionService.viewFunctions();
    }

    @PostMapping("/update/{id}")
    public GlobalResponse updateFunction(@PathVariable String id, @RequestBody FunctionDTO body) {
        return crudFunctionService.updateFunction(id, body);
    }

    @DeleteMapping("/delete/{id}")
    public GlobalResponse deleteFunction(@PathVariable String id) {
        return crudFunctionService.deleteFunction(id);
    }
}
