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

    @GetMapping("/name/{name}")
    public ResponseEntity<MedicationUser> getMedicationUserByName(@PathVariable String name) {
        MedicationUser medicationUser = medicationUserService.getMedicationUserByName(name);
        if (medicationUser != null) {
            return ResponseEntity.ok(medicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userName}/medications")
    public ResponseEntity<MedicationUser> addMedicationToList(
            @PathVariable String userName,
            @RequestBody Medication medication
    ) {
        return medicationUserService.addMedicationToList(userName, medication);
    }

    @PutMapping("/{userName}/medications/{medicationName}")
    public ResponseEntity<MedicationUser> updateMedicationInList(
            @PathVariable String userName,
            @PathVariable String medicationName,
            @RequestBody Medication updatedMedication
    ) {
        return medicationUserService.updateMedicationInList(userName, medicationName, updatedMedication);
    }

    @DeleteMapping("/{userName}/medications/{medicationName}")
    public ResponseEntity<MedicationUser> removeMedicationFromList(
            @PathVariable String userName,
            @PathVariable String medicationName
    ) {
        MedicationUser updatedMedicationUser = medicationUserService.removeMedicationFromList(userName, medicationName);
        if (updatedMedicationUser != null) {
            return ResponseEntity.ok(updatedMedicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
