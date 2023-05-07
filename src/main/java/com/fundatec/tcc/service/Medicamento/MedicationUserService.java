package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.model.Medication;
import com.fundatec.tcc.model.MedicationUser;
import com.fundatec.tcc.repository.MedicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MedicationUserService {
    @Autowired
    private MedicationUserRepository medicationRepository;

    private List<Medication> medications = new ArrayList<>();

    @Autowired
    private MedicationUserRepository medicationUserRepository;

    public Medication createMedication(Medication medication, String userId) {
        Optional<MedicationUser> medicationUserOptional = medicationUserRepository.findById(userId);
        if (medicationUserOptional.isPresent()) {
            MedicationUser medicationUser = medicationUserOptional.get();
            List<Medication> medicationList = medicationUser.getMedicationList();
            medicationList.add(medication);
            medicationUserRepository.save(medicationUser);
        }
        return medication;
    }
    public Medication updateMedication(Medication medication, String userId) {
        Optional<MedicationUser> medicationUserOptional = medicationUserRepository.findById(userId);
        if (medicationUserOptional.isPresent()) {
            MedicationUser medicationUser = medicationUserOptional.get();
            List<Medication> medicationList = medicationUser.getMedicationList();
            int index = medicationList.indexOf(medication);
            if (index != -1) {
                medicationList.set(index, medication);
                medicationUserRepository.save(medicationUser);
            }
        }
        return medication;
    }

    public List<MedicationUser> findAllMedicationUsers() {
        return medicationUserRepository.findAll();
    }

    public Optional<MedicationUser> findMedicationUserById(String medicationUserId) {
        return medicationUserRepository.findById(medicationUserId);
    }
//    public List<Medication> findMedicationsByUserIdThymeleaft(String userId) {
//        Optional<MedicationUser> medicationUserOptional = medicationUserRepository.findById(userId);
//        if (medicationUserOptional.isPresent()) {
//            MedicationUser medicationUser = medicationUserOptional.get();
//            return medicationUser.getMedicationList();
//        } else {
//            return Collections.emptyList();
//        }
//    }
    public void deleteMedicationUser(String medicationUserId) {
        medicationUserRepository.deleteById(medicationUserId);
    }
    public Medication updateExistingMedication(String medicationUserId, String medicationId, Medication updatedMedication) {
        Optional<MedicationUser> medicationUserOptional = medicationUserRepository.findById(medicationUserId);
        if (medicationUserOptional.isPresent()) {
            MedicationUser medicationUser = medicationUserOptional.get();
            List<Medication> medicationList = medicationUser.getMedicationList();
            Optional<Medication> existingMedicationOptional = medicationList.stream()
                    .filter(medication -> medication.getId().equals(medicationId))
                    .findFirst();
            if (existingMedicationOptional.isPresent()) {
                Medication existingMedication = existingMedicationOptional.get();
                existingMedication.setName(updatedMedication.getName());
                existingMedication.setDosage(updatedMedication.getDosage());
                existingMedication.setAmount(updatedMedication.getAmount());
                existingMedication.setDateRegister(updatedMedication.getDateRegister());
                existingMedication.setTime(updatedMedication.getTime());
                medicationUserRepository.save(medicationUser);
                return existingMedication;
            }
        }
        return null;
    }
    public void addMedicationsToList(String medicationUserId, List<Medication> medications) {
        Optional<MedicationUser> medicationUserOptional = medicationUserRepository.findById(medicationUserId);
        if (medicationUserOptional.isPresent()) {
            MedicationUser medicationUser = medicationUserOptional.get();
            List<Medication> medicationList = medicationUser.getMedicationList();
            medicationList.addAll(medications);
            medicationUserRepository.save(medicationUser);
        }
    }
    public void removeMedicationFromList(String medicationUserId, Medication medication) {
        Optional<MedicationUser> medicationUserOptional = medicationUserRepository.findById(medicationUserId);
        if (medicationUserOptional.isPresent()) {
            MedicationUser medicationUser = medicationUserOptional.get();
            List<Medication> medicationList = medicationUser.getMedicationList();
            medicationList.remove(medication);
            medicationUserRepository.save(medicationUser);
        }
    }
    public void updateMedicationInList(String medicationUserId, Medication oldMedication, Medication newMedication) {
        Optional<MedicationUser> medicationUserOptional = medicationUserRepository.findById(medicationUserId);
        if (medicationUserOptional.isPresent()) {
            MedicationUser medicationUser = medicationUserOptional.get();
            List<Medication> medicationList = medicationUser.getMedicationList();
            int index = medicationList.indexOf(oldMedication);
            if (index != -1) {
                medicationList.set(index, newMedication);
                medicationUserRepository.save(medicationUser);
            }
        }
    }
}