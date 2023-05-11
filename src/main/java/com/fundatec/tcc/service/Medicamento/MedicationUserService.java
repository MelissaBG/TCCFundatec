package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;
import com.fundatec.tcc.repository.MedicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public MedicationUser saveMedicationUser(MedicationUser medicationUser) {
        return medicationUserRepository.save(medicationUser);
    }

    public MedicationUser removeMedicationFromList(String id, String medicationName) {
        MedicationUser medicationUser = getMedicationUserById(id);
        for (String medication : medicationUser.getMedications()) {
            if(medication.equals(medicationName)){
                medicationUser.getMedications().remove(medication);
            }
        }
        return medicationUserRepository.save(medicationUser);
    }

}
