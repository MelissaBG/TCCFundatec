package com.fundatec.tcc.controller;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.service.Medicamento.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping("/getAllMedication")
    public ResponseEntity<List<Medication>> getAllMedication() {
        List<Medication> medication = medicationService.getAllMedication();
        return ResponseEntity.ok(medication);
    }

    @PostMapping("/saveMedication")
    public ResponseEntity<Medication> saveMedication(@RequestBody Medication medication) {
        Medication savedMedication = medicationService.saveMedication(medication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedication);
    }

    @PutMapping("/updateMedication/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable String id, @RequestBody Medication medication) {
        Medication updatedMedication = medicationService.updateMedication(id, medication);
        if (updatedMedication != null) {
            return ResponseEntity.ok(updatedMedication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteMedicationById/{id}")
    public ResponseEntity<Void> deleteMedicationById(@PathVariable String id) {
        medicationService.deleteMedicationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable String id) {
        Optional<Medication> medication = medicationService.findById(id);
        return medication.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Medication> getMedicationByName(@PathVariable String name) {
        Medication medication = medicationService.findByName(name);
        if (medication != null) {
            return new ResponseEntity<>(medication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}