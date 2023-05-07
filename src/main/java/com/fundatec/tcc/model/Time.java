package com.fundatec.tcc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;
@Document(collection = "time")
public class Time {
    @Id
    private String id;
    private DayOfWeek dayOfWeek;
    private LocalTime hour;

    public Time() {}

    public Time(String id, DayOfWeek dayOfWeek, LocalTime hour) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }
}

