package com.bmi.internship.example.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmi.internship.example.entity.Unit;
import com.bmi.internship.example.model.UnitDTO;
import com.bmi.internship.example.model.GlobalResponse;
import com.bmi.internship.example.repository.FunctionRepo;
import com.bmi.internship.example.repository.UnitRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class CRUDUnitService {

    @Autowired
    UnitRepo repo;

    @Autowired
    private UnitRepo unitRepo;
    
    @Autowired
    private FunctionRepo functionRepo;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public GlobalResponse deleteAllUnits() {
        GlobalResponse response = new GlobalResponse();
        try {
            // Hapus semua entri di tabel Function terlebih dahulu
            functionRepo.deleteAll();
            // Reset sequence ID untuk tabel Function
            Query resetFunctionSequence = entityManager.createNativeQuery("ALTER SEQUENCE sq_function RESTART WITH 1");
            resetFunctionSequence.executeUpdate();

            // Hapus semua entri di tabel Unit
            unitRepo.deleteAll();
            // Reset sequence ID untuk tabel Unit
            Query resetUnitSequence = entityManager.createNativeQuery("ALTER SEQUENCE sq_unit RESTART WITH 1");
            resetUnitSequence.executeUpdate();

            response.setStatus("success");
            response.setDescription("All units and functions deleted and sequences reset successfully");
        } catch (Exception e) {
            response.setStatus("error");
            response.setDescription("Error while deleting all units and functions and resetting sequences");
            response.setDetails(e.getMessage());
            throw new RuntimeException(e);
        }
        return response;
    }

    public List<Unit> findAllUnits() {
        return unitRepo.findAll();
    }
}
