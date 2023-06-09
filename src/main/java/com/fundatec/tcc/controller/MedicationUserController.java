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
@RequestMapping("/medication_users")
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
    @PostMapping("/save")
    public ResponseEntity<MedicationUser> saveMedicationUser(@RequestBody MedicationUser medicationUser) {
        MedicationUser savedMedicationUser = medicationUserService.saveMedicationUser(medicationUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedicationUser);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MedicationUser> updateMedicationUser(@PathVariable String id, @RequestBody MedicationUser medicationUser) {
        MedicationUser updatedMedicationUser = medicationUserService.updateMedicationUser(id, medicationUser);
        if (updatedMedicationUser != null) {
            return ResponseEntity.ok(updatedMedicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicationUserById(@PathVariable String id) {
        medicationUserService.deleteMedicationUserById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/add_medications/{medicationUserId}")
    public ResponseEntity<MedicationUser> addMedicationsToList(@PathVariable String medicationUserId, @RequestBody Medication medication) {
        MedicationUser updatedUser = medicationUserService.addMedicationsToList(medicationUserId, medication);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove_medications/{id}")
    public ResponseEntity<MedicationUser> removeMedicationFromList(@PathVariable String id, @RequestBody String medicationId) {
        MedicationUser updatedUser = medicationUserService.removeMedicationFromList(id, medicationId);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

