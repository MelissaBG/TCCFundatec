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
    public ResponseEntity<MedicationUser> saveMedicationUser(@RequestBody MedicationUser medicationUser) {
        MedicationUser savedMedicationUser = medicationUserService.saveMedicationUser(medicationUser);
        return new ResponseEntity<>(savedMedicationUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/medications/{medicationName}")
    public ResponseEntity<MedicationUser> removeMedicationFromList(@PathVariable String id, @PathVariable String medicationName) {
        MedicationUser updatedMedicationUser = medicationUserService.removeMedicationFromList(id, medicationName);
        if (updatedMedicationUser != null) {
            return new ResponseEntity<>(updatedMedicationUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

