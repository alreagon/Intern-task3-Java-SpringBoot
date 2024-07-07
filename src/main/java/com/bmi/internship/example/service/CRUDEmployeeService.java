package com.bmi.internship.example.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmi.internship.example.Utils.JsonUtil;
import com.bmi.internship.example.entity.Employee;
import com.bmi.internship.example.model.EmployeeDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.repository.EmployeeRepo;

@Service
public class CRUDEmployeeService {
    @Autowired
    EmployeeRepo repo;

    public GlobalResponse createEmployee(EmployeeDTO body) {
        GlobalResponse response = new GlobalResponse();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // ZonedDateTime x = new ZonedDateTime(timestamp);
        try {
            Employee data = new Employee();
            data.setCreated(timestamp);
            data.setName(body.getNamaKaryawan());
            data.setUnit("Digital Banking");
            data.setFunction(body.getTitelPekerjaan());
            data.setEmployeeid(body.getNikKaryawan());
            System.out.println("Data to be submitted : " + JsonUtil.convertObjectToString(data));
            repo.save(data);
            response.setStatus("success");
            response.setDescription("data submitted successfully");
            response.setDetails(data);
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while submitting data");
            response.setDetails(e);
            // TODO: handle exception
        }
        return response;
    }

    public GlobalResponse viewEmployee() {
        GlobalResponse response = new GlobalResponse();
        try {
            List<Employee> result = repo.findAll();
            response.setStatus("success");
            response.setDescription("result returned");
            response.setDetails(result);
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error when return data");
            response.setDetails(e);
            // TODO: handle exception
        }
        return response;
    }

    public GlobalResponse updateEmployee(Long id, EmployeeDTO body) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Employee> employeeOptional = repo.findById(id);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                employee.setName(body.getNamaKaryawan());
                employee.setFunction(body.getTitelPekerjaan());
                employee.setEmployeeid(body.getNikKaryawan());
                repo.save(employee);
                response.setStatus("success");
                response.setDescription("employee data updated successfully");
                response.setDetails(employee);
            } else {
                response.setStatus("error");
                response.setDescription("employee not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while updating employee data");
            response.setDetails(e);
        }
        return response;
    }
    

    public GlobalResponse deleteEmployee(Long id) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Employee> employeeOptional = repo.findById(id);
            if (employeeOptional.isPresent()) {
                repo.deleteById(id);
                response.setStatus("success");
                response.setDescription("employee deleted successfully");
            } else {
                response.setStatus("error");
                response.setDescription("employee not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while deleting employee data");
            response.setDetails(e);
        }
        return response;
    }
    
    
}
