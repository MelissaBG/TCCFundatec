package com.fundatec.tcc.controller;

import com.fundatec.tcc.controller.exceptions.MedicationAlreadyExistsException;
import com.fundatec.tcc.controller.exceptions.MedicationNotFoundException;
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

    @GetMapping
    public List<Medication> getAllMedications() throws MedicationNotFoundException {
        List<Medication> medications = medicationService.getAllMedications();
        if (medications.isEmpty()) {
            throw new MedicationNotFoundException("No medications found");
        } else {
            return medications;
        }
    }

    @GetMapping("/getMedicationByName/{name}")
    public ResponseEntity<Medication> getMedicationByName(@PathVariable String name) throws MedicationNotFoundException {
        Medication medication = medicationService.findByName(name);
        if (medication != null) {
            return new ResponseEntity<>(medication, HttpStatus.OK);
        } else {
            throw new MedicationNotFoundException("Medication with name " + name + " not found");
        }
    }

    @PostMapping("/createMedication")
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) throws MedicationAlreadyExistsException {
        if (medicationService.findByName(medication.getName()) != null) {
            throw new MedicationAlreadyExistsException("Medication " + medication.getName() + " already exists");
        }
        Medication savedMedication = medicationService.saveMedication(medication);
        return new ResponseEntity<>(savedMedication, HttpStatus.CREATED);
    }

    @PutMapping("/updateMedication/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable String id, @RequestBody Medication medication) throws MedicationNotFoundException {
        Medication existingMedication = medicationService.getMedicationById(id);
        if (existingMedication != null) {
            existingMedication.setName(medication.getName());
            existingMedication.setDosage(medication.getDosage());
            existingMedication.setAmount(medication.getAmount());
            Medication updatedMedication = medicationService.saveMedication(existingMedication);
            return new ResponseEntity<>(updatedMedication, HttpStatus.OK);
        } else {
            throw new MedicationNotFoundException("Medication with ID " + id + " not found");
        }
    }

    @DeleteMapping("/deleteMedication/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable String id) throws MedicationNotFoundException {
        Medication existingMedication = medicationService.getMedicationById(id);
        if (existingMedication != null) {
            medicationService.deleteMedicationById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new MedicationNotFoundException("Medication with ID " + id + " not found");
        }
    }

}
