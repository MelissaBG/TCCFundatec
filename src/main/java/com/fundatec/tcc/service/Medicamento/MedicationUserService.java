package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.controller.exceptions.MedicationAlreadyExistsException;
import com.fundatec.tcc.controller.exceptions.MedicationNotFoundException;
import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;
import com.fundatec.tcc.repository.MedicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public MedicationUser updateMedicationUser(String id, MedicationUser medicationUser) {
        if (medicationUserRepository.existsById(id)) {
            MedicationUser existingMedicationUser = medicationUserRepository.findById(id).orElse(null);
            if (existingMedicationUser != null) {
                existingMedicationUser.setMedicationList(medicationUser.getMedicationList());
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
            List<Medication> medicationList = medicationUser.getMedicationList();
            medicationList.addAll(medications);
            medicationUser.setMedicationList(medicationList);
            return medicationUserRepository.save(medicationUser);
        }
        return null;
    }
    public MedicationUser removeMedicationFromList(String medicationUserId, String medicationId) {
        MedicationUser medicationUser = medicationUserRepository.findById(medicationUserId).orElse(null);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            medicationList.removeIf(medication -> medication.getId().equals(medicationId));
            medicationUser.setMedicationList(medicationList);
            return medicationUserRepository.save(medicationUser);
        }
        return null;
    }

    private Medication getMedicationById(List<Medication> medicationList, String medicationId) throws MedicationNotFoundException {
        for (Medication medication : medicationList) {
            if (medication.getId().equals(medicationId)) {
                return medication;
            }
        }
        throw new MedicationNotFoundException("Medication not found.");
    }
    public MedicationUser updateMedicationInList(String medicationUserId, Medication updatedMedication) {
        MedicationUser medicationUser = medicationUserRepository.findById(medicationUserId).orElse(null);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            for (int i = 0; i < medicationList.size(); i++) {
                Medication medication = medicationList.get(i);
                if (medication.getId().equals(updatedMedication.getId())) {
                    medicationList.set(i, updatedMedication);
                    medicationUser.setMedicationList(medicationList);
                    return medicationUserRepository.save(medicationUser);
                }
            }
        }
        return null;
    }
}

