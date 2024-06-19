package com.example.zootest.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Visit {
    private Long id;
    private LocalDateTime date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> enclosureIds; // IDs des enclos visités

    public Visit() {
    }

    public Visit(Long id, LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, List<Long> enclosureIds) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enclosureIds = enclosureIds;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Long> getEnclosureIds() {
        return enclosureIds;
    }

    public void setEnclosureIds(List<Long> enclosureIds) {
        this.enclosureIds = enclosureIds;
    }
}

