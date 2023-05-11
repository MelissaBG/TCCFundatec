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
        Optional<MedicationUser> medicationUser = medicationUserRepository.findById(id);
        return medicationUser.orElse(null);
    }

    public MedicationUser saveMedicationUser(MedicationUser medicationUser) {
        return medicationUserRepository.save(medicationUser);
    }

    public MedicationUser updateMedicationUser(String id, MedicationUser medicationUser) {
        if (medicationUserRepository.existsById(id)) {
            MedicationUser existingMedicationUser = medicationUserRepository.findById(id).orElse(null);
            if (existingMedicationUser != null) {
                existingMedicationUser.setMedicationNameList(medicationUser.getMedicationNameList());
                return medicationUserRepository.save(existingMedicationUser);
            }
        }
        return null;
    }

    public void deleteMedicationUserById(String id) {
        medicationUserRepository.deleteById(id);
    }

    public MedicationUser addMedicationsToList(String medicationUserId, List<Medication> medications) {
        MedicationUser medicationUser = medicationUserRepository.findById(medicationUserId).orElse(null);
        if (medicationUser != null) {
            List<String> medicationNameList = medicationUser.getMedicationNameList();
            for (Medication medication : medications) {
                medicationNameList.add(medication.getName());
            }
            medicationUser.setMedicationNameList(medicationNameList);
            return medicationUserRepository.save(medicationUser);
        }
        return null;
    }

    public MedicationUser removeMedicationFromList(String Id, String medicationId) {
        MedicationUser medicationUser = medicationUserRepository.findById(Id).orElse(null);
        if (medicationUser != null) {
            List<String> medicationNameList = medicationUser.getMedicationNameList();
            medicationNameList.removeIf(medication -> medication.equals(medicationId));
            medicationUser.setMedicationNameList(medicationNameList);
            return medicationUserRepository.save(medicationUser);
        }
        return null;
    }

    public MedicationUser updateMedicationInList(String medicationUserId, String medicationId, Medication updatedMedication) {
        MedicationUser medicationUser = medicationUserRepository.findById(medicationUserId).orElse(null);
        if (medicationUser != null) {
            List<String> medicationNameList = medicationUser.getMedicationNameList();
            for (int i = 0; i < medicationNameList.size(); i++) {
                if (medicationNameList.get(i).equals(medicationId)) {
                    medicationNameList.set(i, updatedMedication.getName());
                    medicationUser.setMedicationNameList(medicationNameList);
                    return medicationUserRepository.save(medicationUser);
                }
            }
        }
        return null;
    }
}
