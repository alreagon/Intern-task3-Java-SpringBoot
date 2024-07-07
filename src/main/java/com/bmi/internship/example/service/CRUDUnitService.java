package com.bmi.internship.example.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmi.internship.example.entity.Unit;
import com.bmi.internship.example.model.UnitDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.repository.UnitRepo;

@Service
public class CRUDUnitService {
    @Autowired
    UnitRepo repo;

    public GlobalResponse createUnit(UnitDTO body) {
        GlobalResponse response = new GlobalResponse();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            Unit unit = new Unit();
            unit.setCreated(timestamp);
            unit.setAbbreviation(body.getAbbreviation());
            unit.setUnit(body.getUnit());
            repo.save(unit);
            response.setStatus("success");
            response.setDescription("Unit created successfully");
            response.setDetails(unit);
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while creating unit");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse viewUnits() {
        GlobalResponse response = new GlobalResponse();
        try {
            List<Unit> result = repo.findAll();
            response.setStatus("success");
            response.setDescription("Units retrieved successfully");
            response.setDetails(result);
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while retrieving units");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse updateUnit(Long id, UnitDTO body) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Unit> unitOptional = repo.findById(id);
            if (unitOptional.isPresent()) {
                Unit unit = unitOptional.get();
                unit.setAbbreviation(body.getAbbreviation());
                unit.setUnit(body.getUnit());
                repo.save(unit);
                response.setStatus("success");
                response.setDescription("Unit updated successfully");
                response.setDetails(unit);
            } else {
                response.setStatus("error");
                response.setDescription("Unit not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while updating unit");
            response.setDetails(e);
        }
        return response;
    }

    public GlobalResponse deleteUnit(Long id) {
        GlobalResponse response = new GlobalResponse();
        try {
            Optional<Unit> unitOptional = repo.findById(id);
            if (unitOptional.isPresent()) {
                repo.deleteById(id);
                response.setStatus("success");
                response.setDescription("Unit deleted successfully");
            } else {
                response.setStatus("error");
                response.setDescription("Unit not found with id: " + id);
            }
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while deleting unit");
            response.setDetails(e);
        }
        return response;
    }
}
