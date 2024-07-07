package com.bmi.internship.example.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.List;

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
}
