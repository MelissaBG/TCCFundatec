package com.fundatec.tcc.service.Time;

import com.fundatec.tcc.model.Time;
import com.fundatec.tcc.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public Time create(Time time) {
        return timeRepository.save(time);
    }

    public Optional<Time> getById(String id) {
        return timeRepository.findById(id);
    }

    public List<Time> getAll() {
        return timeRepository.findAll();
    }

    public Time update(Time time) {
        return timeRepository.save(time);
    }

    public void deleteById(String id) {
        timeRepository.deleteById(id);
    }
}
