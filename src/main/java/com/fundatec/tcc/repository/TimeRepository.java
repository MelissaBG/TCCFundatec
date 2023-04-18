package com.fundatec.tcc.repository;

import com.fundatec.tcc.model.Time;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeRepository extends MongoRepository<Time, String> {
}
