package com.fundatec.tcc.controller;

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
    public List<MedicationUser> getAllMedicationUsers() {
        return medicationUserService.getAllMedicationUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationUser> getMedicationUserById(@PathVariable String id) {
        MedicationUser medicationUser = medicationUserService.getMedicationUserById(id);
        if (medicationUser != null) {
            return new ResponseEntity<>(medicationUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MedicationUser> addMedicationToList(
            @PathVariable String userId,
            @RequestBody Medication medication
    ) {
        MedicationUser medicationUser = medicationUserService.addMedicationToList(userId, medication);
        if (medicationUser != null) {
            return ResponseEntity.ok(medicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{userId}/medications/{medicationId}")
    public ResponseEntity<MedicationUser> updateMedicationInList(
            @PathVariable String userId,
            @PathVariable String medicationId,
            @RequestBody Medication updatedMedication
    ) {
        MedicationUser medicationUser = medicationUserService.updateMedicationInList(userId, medicationId, updatedMedication);
        if (medicationUser != null) {
            return ResponseEntity.ok(medicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/medications/{medicationName}")
    public ResponseEntity<MedicationUser> removeMedicationFromList(
            @PathVariable String id,
            @PathVariable String medicationName
    ) {
        MedicationUser updatedMedicationUser = medicationUserService.removeMedicationFromList(id, medicationName);
        if (updatedMedicationUser != null) {
            return ResponseEntity.ok(updatedMedicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

