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

    public MedicationUser addMedicationToUser(String userName, Medication medication) throws MedicationAlreadyExistsException {
        MedicationUser medicationUser = medicationUserRepository.findByUserName(userName);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            boolean medicationExists = medicationList.stream()
                    .anyMatch(m -> m.getId().equals(medication.getId()));

            if (medicationExists) {
                throw new MedicationAlreadyExistsException(String.format("Medication with is already in the list", medication.getId()));
            }

            medicationList.add(medication);
            medicationUser.setMedicationList(medicationList);
            medicationUserRepository.save(medicationUser);
        }

        return medicationUser;
    }
    public void removeMedicationFromUser(String userName, String medicationId) throws MedicationNotFoundException {
        MedicationUser user = medicationUserRepository.findByUserName(userName);
        if (user == null) {
            throw new MedicationNotFoundException("User not found.");
        }
        List<Medication> medicationList = user.getMedicationList();
        Medication medicationToRemove = getMedicationById(medicationList, medicationId);
        if (medicationToRemove == null) {
            throw new MedicationNotFoundException("Medication not found in user list.");
        }
        medicationList.remove(medicationToRemove);
        medicationUserRepository.save(user);
    }

    private Medication getMedicationById(List<Medication> medicationList, String medicationId) throws MedicationNotFoundException {
        for (Medication medication : medicationList) {
            if (medication.getId().equals(medicationId)) {
                return medication;
            }
        }
        throw new MedicationNotFoundException("Medication not found.");
    }
    public void updateMedicationFromUser(String userName, Medication updatedMedication) throws MedicationNotFoundException {
        MedicationUser user = medicationUserRepository.findByUserName(userName);
        if (user == null) {
            throw new MedicationNotFoundException("User not found.");
        }
        List<Medication> medicationListUser = user.getMedicationList();
        boolean medicationExists = false;
        for (Medication medication : medicationListUser) {
            if (medication.getId().equals(updatedMedication.getId())) {
                medication.setDosage(updatedMedication.getDosage());
                medication.setAmount(updatedMedication.getAmount());
                medication.setDateRegister(updatedMedication.getDateRegister());
                medication.setTime(updatedMedication.getTime());

                medicationExists = true;
                break;
            }
        }

        if (!medicationExists) {
            throw new MedicationNotFoundException("Medication not found in user list.");
        }
        medicationUserRepository.save(user);
    }
}

