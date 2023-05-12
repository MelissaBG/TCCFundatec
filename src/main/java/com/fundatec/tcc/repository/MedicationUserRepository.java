package com.fundatec.tcc.repository;

import com.fundatec.tcc.model.MedicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationUserRepository extends MongoRepository<MedicationUser, String> {
    MedicationUser findByUserName(String userName);


}
