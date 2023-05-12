package com.fundatec.tcc.service.Medicamento;

import com.fundatec.tcc.controller.exceptions.MedicationAlreadyExistsException;
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

    public void addMedicationToUser(String userName, Medication medication) throws MedicationAlreadyExistsException {
        MedicationUser medicationUser = medicationUserRepository.findByUserName(userName);
        if (medicationUser != null) {
            List<Medication> medicationListUser = medicationUser.getMedicationList();
            Medication findMedication = medicationListUser.stream()
                    .filter(m -> m.getName().equals(medication.getName()))
                    .findFirst()
                    .orElse(null);

            if (findMedication != null) {
                throw new MedicationAlreadyExistsException(String.format("A medicação  já está na lista", medication.getName()));
            }

            medicationListUser.add(medication);
            medicationUser.setMedicationList(medicationListUser);
            medicationUserRepository.save(medicationUser);
        }
    }
}

