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

    public MedicationUser addMedicationToUser(String userName, Medication medication) throws MedicationAlreadyExistsException {
        MedicationUser medicationUser = medicationUserRepository.findByUserName(userName);
        if (medicationUser != null) {
            List<Medication> medicationList = medicationUser.getMedicationList();
            Medication findMedication = medicationList.stream()
                    .filter(m -> m.getName().equals(medication.getName()))
                    .findFirst()
                    .orElse(null);

            if (findMedication != null) {
                throw new MedicationAlreadyExistsException(String.format("A medicação %s já está na lista", medication.getName()));
            }

            medicationList.add(medication);
            medicationUser.setMedicationList(medicationList);
            medicationUserRepository.save(medicationUser);
        }

        return medicationUser;
    }
    public void removeMedicationFromUser(String userName, String medicationName) throws MedicationNotFoundException{
            MedicationUser user = medicationUserRepository.findByUserName(userName);
            if (user == null) {
                throw new MedicationNotFoundException("Usuário não encontrado");
            }

            List<Medication> medicationList = user.getMedicationList();
        Medication medicationToRemove = getMedicationByName(medicationList, medicationName);
        if (medicationToRemove == null) {
                throw new MedicationNotFoundException("Medicação não encontrada na lista do usuário");
            }
            medicationList.remove(medicationToRemove);
            medicationUserRepository.save(user);
        }

    private Medication getMedicationByName(List<Medication> medicationList, String medicationName) throws MedicationNotFoundException {
        for (Medication medication : medicationList) {
            if (medication.getName().equals(medicationName)) {
                return medication;
            }
        }
        throw new MedicationNotFoundException("Medicação não encontrada");
    }

}

