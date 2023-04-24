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
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping("/save")
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        Medication createdMedication = medicationService.createMedication(medication);
        return new ResponseEntity<>(createdMedication, HttpStatus.CREATED);
    }

    @GetMapping("/getAllMedications")
    public ResponseEntity<List<Medication>> getAllMedications() {
        List<Medication> medications = medicationService.getAllMedications();
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }

    @GetMapping("getMedicationById/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable String id) {
        Optional<Medication> medicationOptional = medicationService.getMedicationById(id);

        if (!medicationOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Medication medication = medicationOptional.get();
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

    @DeleteMapping("deleteMedication/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable String id) {
        medicationService.deleteMedication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("updateMedication/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable String id, @RequestBody Medication medication) {
        Medication updatedMedication = medicationService.updateMedication(id, medication);
        return new ResponseEntity<>(updatedMedication, HttpStatus.OK);
    }
    @PostMapping("addMedicationsToList/{id}")
    public ResponseEntity<Medication> addMedicationsToList(@PathVariable String id, @RequestBody List<Medication> medications) {
        medicationService.addMedicationsToList(id, medications);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("removeMedicationFromList/{id}")
    public ResponseEntity<Medication> removeMedicationFromList(@PathVariable String id, @RequestBody Medication medication) {
        medicationService.removeMedicationFromList(id, medication);
        return ResponseEntity.ok().build();
    }

    @PutMapping("updateMedicationInList/{id}")
    public ResponseEntity<Medication> updateMedicationInList(@PathVariable String id, @RequestBody Medication oldMedication, @RequestBody Medication newMedication) {
        medicationService.updateMedicationInList(id, oldMedication, newMedication);
        return ResponseEntity.ok().build();
    }
}

