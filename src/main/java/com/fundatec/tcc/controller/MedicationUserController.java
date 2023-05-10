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

    @GetMapping("/getAllMedicationUsers")
    public ResponseEntity<List<MedicationUser>> getAllMedicationUsers() {
        List<MedicationUser> medicationUsers = medicationUserService.getAllMedicationUsers();
        return ResponseEntity.ok(medicationUsers);
    }

    @GetMapping("/getMedicationUserById/{id}")
    public ResponseEntity<MedicationUser> getMedicationUserById(@PathVariable String id) {
        MedicationUser medicationUser = medicationUserService.getMedicationUserById(id);
        if (medicationUser != null) {
            return ResponseEntity.ok(medicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/saveMedicationUser")
    public ResponseEntity<MedicationUser> saveMedicationUser(@RequestBody MedicationUser medicationUser) {
        MedicationUser savedMedicationUser = medicationUserService.saveMedicationUser(medicationUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicationUser);
    }

    @PutMapping("/updateMedicationUser/{id}")
    public ResponseEntity<MedicationUser> updateMedicationUser(@PathVariable String id, @RequestBody MedicationUser medicationUser) {
        MedicationUser updatedMedicationUser = medicationUserService.updateMedicationUser(id, medicationUser);
        if (updatedMedicationUser != null) {
            return ResponseEntity.ok(updatedMedicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteMedicationUserById/{id}")
    public ResponseEntity<Void> deleteMedicationUserById(@PathVariable String id) {
        medicationUserService.deleteMedicationUserById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{medicationUserId}/addMedications")
    public ResponseEntity<MedicationUser> addMedicationsToList(@PathVariable String id, @RequestBody List<Medication> medications) {
        MedicationUser updatedUser = medicationUserService.addMedicationsToList(id, medications);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{medicationUserId}/removeMedication")
    public ResponseEntity<MedicationUser> removeMedicationFromList(@PathVariable String id, @RequestParam String medicationId) {
        MedicationUser updatedUser = medicationUserService.removeMedicationFromList(id, medicationId);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{medicationUserId}/updateMedication/{medicationId}")
    public ResponseEntity<MedicationUser> updateMedicationInList(@PathVariable String id, @PathVariable String medicationId, @RequestBody Medication updatedMedication) {
        MedicationUser updatedUser = medicationUserService.updateMedicationInList(id, medicationId, updatedMedication);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

