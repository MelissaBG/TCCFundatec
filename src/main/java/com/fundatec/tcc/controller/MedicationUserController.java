package com.fundatec.tcc.controller;

import com.fundatec.tcc.controller.exceptions.MedicationAlreadyExistsException;
import com.fundatec.tcc.controller.exceptions.MedicationNotFoundException;
import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;

import com.fundatec.tcc.service.Medicamento.MedicationService;
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
    @RestController
    @RequestMapping("/medications")
    public class MedicationController {

        @Autowired
        private MedicationService medicationService;

        @GetMapping("/getAllMedications")
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

        @PutMapping("/updateMedication/{medicationName}")
        public ResponseEntity<Medication> updateMedicationByName(
                @PathVariable String medicationName,
                @RequestBody Medication medication
        ) {
            Medication existingMedication = medicationService.getMedicationByName(medicationName);
            if (existingMedication != null) {
                existingMedication.setName(medication.getName());
                existingMedication.setDosage(medication.getDosage());
                existingMedication.setAmount(medication.getAmount());
                Medication updatedMedication = medicationService.saveMedication(existingMedication);
                return ResponseEntity.ok(updatedMedication);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/deleteMedication/{userName}/{medicationName}")
        public ResponseEntity<Void> removeMedicationFromUser(
                @PathVariable String userName,
                @PathVariable String medicationName) {
            try {
                medicationUserService.removeMedicationFromUser(userName, medicationName);
                return ResponseEntity.noContent().build();
            } catch (MedicationNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }

        @PutMapping("/{userName}/medications")
        public ResponseEntity<String> updateMedicationFromUser(
                @PathVariable String userName,
                @RequestBody Medication updatedMedication) {
            try {
                medicationUserService.updateMedicationFromUser(userName, updatedMedication);
                return ResponseEntity.ok("Medicação atualizada com sucesso");
            } catch (MedicationNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
    }
}

