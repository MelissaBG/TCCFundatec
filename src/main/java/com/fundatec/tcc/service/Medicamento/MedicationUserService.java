package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.controller.exceptions.UserNotFoundException;
import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;
import com.fundatec.tcc.repository.MedicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationUserService {
    @Autowired
    private MedicationUserRepository medicationUserRepository;

    public List<MedicationUser> getAllMedicationUsers() {
        return medicationUserRepository.findAll();
    }

    public MedicationUser getMedicationUserById(String id) {
        return medicationUserRepository.findById(id).orElse(null);
    }
    public MedicationUser getMedicationUserByName(String name) {
        return medicationUserRepository.findByName(name);
    }
    public ResponseEntity<MedicationUser> addMedicationToList(String userName, Medication medication) {
        MedicationUser medicationUser = medicationUserRepository.findByName(userName);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            medicationList.add(medication);
            medicationUser.setMedicationList(medicationList);
            MedicationUser updatedMedicationUser = medicationUserRepository.save(medicationUser);
            return ResponseEntity.ok(updatedMedicationUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<MedicationUser> updateMedicationInList(String userName, String medicationName, Medication updatedMedication) {
        MedicationUser medicationUser = medicationUserRepository.findByName(userName);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            for (int i = 0; i < medicationList.size(); i++) {
                Medication medication = medicationList.get(i);
                if (medication.getName().equals(medicationName)) {
                    medicationList.set(i, updatedMedication);
                    medicationUser.setMedicationList(medicationList);
                    MedicationUser updatedUser = medicationUserRepository.save(medicationUser);
                    return ResponseEntity.ok(updatedUser);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    public MedicationUser removeMedicationFromList(String userName, String medicationName) {
        MedicationUser medicationUser = medicationUserRepository.findByName(userName);
        List<Medication> medicationList = medicationUser.getMedicationList();

        Iterator<Medication> iterator = medicationList.iterator();
        while (iterator.hasNext()) {
            Medication medication = iterator.next();
            if (medication.getName().equals(medicationName)) {
                iterator.remove();
            }
        }

        return medicationUserRepository.save(medicationUser);
    }

}
