package com.fundatec.tcc.controller;

import com.fundatec.tcc.model.Time;
import com.fundatec.tcc.service.Time.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping
    public ResponseEntity<Time> create(@RequestBody Time time) {
        Time savedTime = timeService.create(time);
        return new ResponseEntity<>(savedTime, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Time> getById(@PathVariable String id) {
        Optional<Time> optionalTime = timeService.getById(id);
        return optionalTime
                .map(time -> new ResponseEntity<>(time, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Time>> getAll() {
        List<Time> times = timeService.getAll();
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Time> update(@PathVariable String id, @RequestBody Time time) {
        time.setId(id);
        Time updatedTime = timeService.update(time);
        return new ResponseEntity<>(updatedTime, HttpStatus.OK);
    }
}