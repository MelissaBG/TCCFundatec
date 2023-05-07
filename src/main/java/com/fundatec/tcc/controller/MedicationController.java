package com.fundatec.tcc.controller;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;
import com.fundatec.tcc.service.Medicamento.MedicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
//    @GetMapping("/{userId}/medications")
//    public String findMedicationsByUserIdThymeleaf(@PathVariable String userId, Model model) {
//        List<Medication> medications = medicationUserService.findMedicationsByUserIdThymeleaft(userId);
//        model.addAttribute("medications", medications);
//        return "medication-list";
//    }
    @GetMapping("/findMedicationUserById/{id}")
    public ResponseEntity<MedicationUser> findMedicationUserById(@PathVariable String medicationUserId) {
        Optional<MedicationUser> medicationUserOptional = medicationUserService.findMedicationUserById(medicationUserId);
        if (medicationUserOptional.isPresent()) {
            return new ResponseEntity<>(medicationUserOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMedicationUser(@PathVariable String medicationUserId) {
        medicationUserService.deleteMedicationUser(medicationUserId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/updateExistingMedication/{medicationUserId}/userMedication/{medicationId}")
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
    @PostMapping("/addMedicationsToList/{medicationUserId}/userMedication/add")
    public ResponseEntity<Void> addMedicationsToList(@PathVariable String medicationUserId,
                                                     @RequestBody List<Medication> medications) {
        medicationUserService.addMedicationsToList(medicationUserId, medications);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/removeMedicationFromList/{medicationUserId}/userMedication/remove")
    public ResponseEntity<Void> removeMedicationFromList(@PathVariable String medicationUserId,
                                                         @RequestBody Medication medication) {
        medicationUserService.removeMedicationFromList(medicationUserId, medication);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/updateMedicationInList/{medicationUserId}/userMedication/update")
    public ResponseEntity<Void> updateMedicationInList(@PathVariable String medicationUserId,
                                                       @RequestBody Medication oldMedication,
                                                       @RequestBody Medication newMedication) {
        medicationUserService.updateMedicationInList(medicationUserId, oldMedication, newMedication);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}