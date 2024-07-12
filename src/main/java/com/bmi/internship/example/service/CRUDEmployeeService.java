package com.bmi.internship.example.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmi.internship.example.entity.Employee;
import com.bmi.internship.example.entity.Function;
import com.bmi.internship.example.entity.Unit;
import com.bmi.internship.example.model.EmployeeDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.repository.EmployeeRepo;
import com.bmi.internship.example.repository.FunctionRepo;

@Service
public class CRUDEmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    FunctionRepo functionRepo;

    public GlobalResponse createEmployee(EmployeeDTO body) {
        GlobalResponse response = new GlobalResponse();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            Optional<Function> functionOptional = functionRepo.findById(body.getFunctionunitId());
            if (functionOptional.isPresent()) {
                Function function = functionOptional.get();
                if (validateFunctionUnitRelation(function)) {
                    Employee employee = new Employee();
                    employee.setCreated(timestamp);
                    employee.setName(body.getNamaKaryawan());
                    employee.setEmployeeid(body.getNikKaryawan());
                    employee.setFunctionunit(function);
                    employeeRepo.save(employee);
                    response.setStatus("success");
                    response.setDescription("Employee created successfully");
                    response.setDetails(employee);
                } else {
                    response.setStatus("error");
                    response.setDescription("Invalid functionunit for the specified unit abbreviation");
                }
            } else {
                response.setStatus("error");
                response.setDescription("Functionunit not found");
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while creating employee");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse viewEmployees() {
        GlobalResponse response = new GlobalResponse();
        try {
            List<Employee> result = employeeRepo.findAll();
            response.setStatus("success");
            response.setDescription("Employees retrieved successfully");
            response.setDetails(result);
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while retrieving employees");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse updateEmployee(Long id, EmployeeDTO body) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Employee> employeeOptional = employeeRepo.findById(id);
            if (employeeOptional.isPresent()) {
                Optional<Function> functionOptional = functionRepo.findById(body.getFunctionunitId());
                if (functionOptional.isPresent()) {
                    Function function = functionOptional.get();
                    if (validateFunctionUnitRelation(function)) {
                        Employee employee = employeeOptional.get();
                        employee.setName(body.getNamaKaryawan());
                        employee.setEmployeeid(body.getNikKaryawan());
                        employee.setFunctionunit(function);
                        employeeRepo.save(employee);
                        response.setStatus("success");
                        response.setDescription("Employee updated successfully");
                        response.setDetails(employee);
                    } else {
                        response.setStatus("error");
                        response.setDescription("Invalid functionunit for the specified unit abbreviation");
                    }
                } else {
                    response.setStatus("error");
                    response.setDescription("Functionunit not found");
                }
            } else {
                response.setStatus("error");
                response.setDescription("Employee not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while updating employee");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse deleteEmployee(Long id) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Employee> employeeOptional = employeeRepo.findById(id);
            if (employeeOptional.isPresent()) {
                employeeRepo.deleteById(id);
                response.setStatus("success");
                response.setDescription("Employee deleted successfully");
            } else {
                response.setStatus("error");
                response.setDescription("Employee not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while deleting employee");
            response.setDetails(e);
        }
        return response;
    }

    private boolean validateFunctionUnitRelation(Function function) {
        String unitAbbreviation = function.getId().substring(0, 3);
        return function.getUnit().getAbbreviation().equals(unitAbbreviation);
    }
}
