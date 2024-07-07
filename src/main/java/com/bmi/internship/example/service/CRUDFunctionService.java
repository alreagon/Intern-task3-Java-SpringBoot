package com.bmi.internship.example.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmi.internship.example.entity.Function;
import com.bmi.internship.example.model.FunctionDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.repository.FunctionRepo;

@Service
public class CRUDFunctionService {
    @Autowired
    FunctionRepo repo;

    public GlobalResponse createFunction(FunctionDTO body) {
        GlobalResponse response = new GlobalResponse();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            Function function = new Function();
            function.setCreated(timestamp);
            function.setId(body.getId());
            function.setFunction(body.getFunction());
            repo.save(function);
            response.setStatus("success");
            response.setDescription("Function created successfully");
            response.setDetails(function);
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while creating function");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse viewFunctions() {
        GlobalResponse response = new GlobalResponse();
        try {
            List<Function> result = repo.findAll();
            response.setStatus("success");
            response.setDescription("Functions retrieved successfully");
            response.setDetails(result);
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while retrieving functions");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse updateFunction(String id, FunctionDTO body) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Function> functionOptional = repo.findById(id);
            if (functionOptional.isPresent()) {
                Function function = functionOptional.get();
                function.setFunction(body.getFunction());
                repo.save(function);
                response.setStatus("success");
                response.setDescription("Function updated successfully");
                response.setDetails(function);
            } else {
                response.setStatus("error");
                response.setDescription("Function not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while updating function");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse deleteFunction(String id) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Function> functionOptional = repo.findById(id);
            if (functionOptional.isPresent()) {
                repo.deleteById(id);
                response.setStatus("success");
                response.setDescription("Function deleted successfully");
            } else {
                response.setStatus("error");
                response.setDescription("Function not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while deleting function");
            response.setDetails(e);
        }
        return response;
    }
}
