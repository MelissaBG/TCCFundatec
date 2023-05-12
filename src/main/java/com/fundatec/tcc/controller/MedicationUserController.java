package com.fundatec.tcc.controller;

import com.fundatec.tcc.controller.exceptions.MedicationAlreadyExistsException;
import com.fundatec.tcc.controller.exceptions.MedicationNotFoundException;
import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;

import com.fundatec.tcc.service.Medicamento.MedicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/medicationUsers")
public class MedicationUserController {
    @Autowired
    private MedicationUserService medicationUserService;

    @GetMapping
    public ResponseEntity<List<MedicationUser>> getAllMedicationUsers() {
        List<MedicationUser> medicationUsers = medicationUserService.getAllMedicationUsers();
        return ResponseEntity.ok(medicationUsers);
    }
    @PostMapping("/{userName}/medications")
    public ResponseEntity<String> addMedicationToUser(
            @PathVariable("userName") String userName,
            @RequestBody Medication medication
    ) {
        try {
            medicationUserService.addMedicationToUser(userName, medication);
            return ResponseEntity.ok("Medication added successfully.");
        } catch (MedicationAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{userName}/medications/{medicationName}")
    public void removeMedicationFromUser(
            @PathVariable String userName,
            @PathVariable String medicationName) throws MedicationNotFoundException {
        medicationUserService.removeMedicationFromUser(userName, medicationName);
    }
}

