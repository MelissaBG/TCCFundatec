package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.controller.exceptions.UserNotFoundException;
import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;
import com.fundatec.tcc.repository.MedicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public MedicationUser addMedicationToList(String userId, Medication medication) {
        MedicationUser medicationUser = medicationUserRepository.findById(userId).orElse(null);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            medicationList.add(medication);
            medicationUser.setMedicationList(medicationList);
            return medicationUserRepository.save(medicationUser);
        }
        return null;
    }

    public MedicationUser updateMedicationInList(String userId, String medicationId, Medication updatedMedication) {
        MedicationUser medicationUser = medicationUserRepository.findById(userId).orElse(null);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            for (int i = 0; i < medicationList.size(); i++) {
                Medication medication = medicationList.get(i);
                if (medication.getId().equals(medicationId)) {
                    medicationList.set(i, updatedMedication);
                    medicationUser.setMedicationList(medicationList);
                    return medicationUserRepository.save(medicationUser);
                }
            }
        }
        return null;
    }

    public MedicationUser removeMedicationFromList(String id, String medicationName) {
        MedicationUser medicationUser = getMedicationUserById(id);
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
