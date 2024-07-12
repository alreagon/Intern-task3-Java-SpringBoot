package com.bmi.internship.example.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmi.internship.example.entity.Function;
import com.bmi.internship.example.entity.Unit;
import com.bmi.internship.example.model.FunctionDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.repository.FunctionRepo;
import com.bmi.internship.example.repository.UnitRepo;

@Service
public class CRUDFunctionService {
    @Autowired
    FunctionRepo functionRepo;

    @Autowired
    UnitRepo unitRepo;

    @Transactional
    public GlobalResponse createFunction(FunctionDTO body) {
        GlobalResponse response = new GlobalResponse();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            Optional<Function> existingFunction = functionRepo.findById(body.getId());
            if (existingFunction.isPresent()) {
                throw new OptimisticLockingFailureException("Function with ID " + body.getId() + " already exists");
            }
            Unit unit = determineUnit(body.getId());
            if (unit != null) {
                Function function = new Function(body.getId(), body.getFunction(), unit);
                function.setCreated(timestamp);
                functionRepo.save(function);
                response.setStatus("success");
                response.setDescription("Function created successfully");
                response.setDetails(function);
            } else {
                response.setStatus("error");
                response.setDescription("Invalid unit for the specified function ID");
            }
        } catch (OptimisticLockingFailureException e) {
            response.setStatus("error");
            response.setDescription(e.getMessage());
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while creating function");
            response.setDetails(e);
        }
        return response;
    }

    @Transactional
    public GlobalResponse updateFunction(String id, FunctionDTO body) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Function> functionOptional = functionRepo.findById(id);
            if (functionOptional.isPresent()) {
                Function function = functionOptional.get();
                Unit unit = determineUnit(body.getId());
                if (unit != null) {
                    function.setFunction(body.getFunction());
                    function.setUnit(unit);
                    functionRepo.save(function);
                    response.setStatus("success");
                    response.setDescription("Function updated successfully");
                    response.setDetails(function);
                } else {
                    response.setStatus("error");
                    response.setDescription("Invalid unit for the specified function ID");
                }
            } else {
                response.setStatus("error");
                response.setDescription("Function not found with id: " + id);
            }
        } catch (OptimisticLockingFailureException e) {
            response.setStatus("error");
            response.setDescription(e.getMessage());
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while updating function");
            response.setDetails(e);
        }
        return response;
    }

    @Transactional
    public GlobalResponse deleteFunction(String id) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Function> functionOptional = functionRepo.findById(id);
            if (functionOptional.isPresent()) {
                functionRepo.deleteById(id);
                response.setStatus("success");
                response.setDescription("Function deleted successfully");
            } else {
                response.setStatus("error");
                response.setDescription("Function not found with id: " + id);
            }
        } catch (OptimisticLockingFailureException e) {
            response.setStatus("error");
            response.setDescription(e.getMessage());
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while deleting function");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse viewFunctions() {
        GlobalResponse response = new GlobalResponse();
        try {
            List<Function> result = functionRepo.findAll();
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

    private Unit determineUnit(String functionId) {
        if (functionId.startsWith("DIB")) {
            return unitRepo.findByAbbreviation("DIB").orElse(null);
        } else if (functionId.startsWith("ITY")) {
            return unitRepo.findByAbbreviation("ITY").orElse(null);
        }
        return null;
    }
}
