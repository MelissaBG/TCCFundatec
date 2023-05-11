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
    public ResponseEntity<List<MedicationUser>> getAllMedicationUsers() {
        List<MedicationUser> medicationUsers = medicationUserService.getAllMedicationUsers();
        return ResponseEntity.ok(medicationUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationUser> getMedicationUserById(@PathVariable String id) {
        MedicationUser medicationUser = medicationUserService.getMedicationUserById(id);
        if (medicationUser != null) {
            return ResponseEntity.ok(medicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}/medications")
    public ResponseEntity<MedicationUser> addMedicationToList(
            @PathVariable String userId,
            @RequestBody Medication medication
    ) {
        return medicationUserService.addMedicationToList(userId, medication);
    }

    @PutMapping("/{userId}/medications/{medicationId}")
    public ResponseEntity<MedicationUser> updateMedicationInList(
            @PathVariable String userId,
            @PathVariable String medicationId,
            @RequestBody Medication updatedMedication
    ) {
        return medicationUserService.updateMedicationInList(userId, medicationId, updatedMedication);
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


