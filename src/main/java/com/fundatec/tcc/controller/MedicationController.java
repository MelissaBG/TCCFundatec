package com.fundatec.tcc.controller;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;
import com.fundatec.tcc.service.Medicamento.MedicationUserService;
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
    private MedicationUserService medicationUserService;
    @PostMapping("/{userId}/create")
    public ResponseEntity<Medication> createMedication(@PathVariable String medicationUserId, @RequestBody Medication medication) {
        Medication createdMedication = medicationUserService.createMedication(medication, medicationUserId);
        return new ResponseEntity<>(createdMedication, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<Medication> updateMedication(@PathVariable String medicationUserId, @RequestBody Medication medication) {
        Medication updatedMedication =  medicationUserService.updateMedication(medication, medicationUserId);
        if (updatedMedication != null) {
            return new ResponseEntity<>(updatedMedication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MedicationUser>> findAllMedicationUsers() {
        List<MedicationUser> medicationUsers = medicationUserService.findAllMedicationUsers();
        return new ResponseEntity<>(medicationUsers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MedicationUser> findMedicationUserById(@PathVariable String medicationUserId) {
        Optional<MedicationUser> medicationUserOptional = medicationUserService.findMedicationUserById(medicationUserId);
        if (medicationUserOptional.isPresent()) {
            return new ResponseEntity<>(medicationUserOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicationUser(@PathVariable String medicationUserId) {
        medicationUserService.deleteMedicationUser(medicationUserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{medicationUserId}/medications/{medicationId}")
    public ResponseEntity<Medication> updateExistingMedication(@PathVariable String medicationUserId,
                                                               @PathVariable String medicationId,
                                                               @RequestBody Medication updatedMedication) {
        Medication updated = medicationUserService.updateExistingMedication(medicationUserId, medicationId, updatedMedication);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{medicationUserId}/medications/add")
    public ResponseEntity<Void> addMedicationsToList(@PathVariable String medicationUserId,
                                                     @RequestBody List<Medication> medications) {
        medicationUserService.addMedicationsToList(medicationUserId, medications);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{medicationUserId}/medications/remove")
    public ResponseEntity<Void> removeMedicationFromList(@PathVariable String medicationUserId,
                                                         @RequestBody Medication medication) {
        medicationUserService.removeMedicationFromList(medicationUserId, medication);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/{medicationUserId}/medications/update")
    public ResponseEntity<Void> updateMedicationInList(@PathVariable String medicationUserId,
                                                       @RequestBody Medication oldMedication,
                                                       @RequestBody Medication newMedication) {
        medicationUserService.updateMedicationInList(medicationUserId, oldMedication, newMedication);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}