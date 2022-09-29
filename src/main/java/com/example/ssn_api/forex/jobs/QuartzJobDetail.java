package com.example.ssn_api.forex.jobs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class QuartzJobDetail {
    private String name;
    private String group;
    @JsonFormat(pattern = "dd-MM-yyyy HH-mm-ss")
    private LocalDateTime startFrom;
    private int numberOfIteration;
    private int interval;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(LocalDateTime startFrom) {
        this.startFrom = startFrom;
    }

    public int getNumberOfIteration() {
        return numberOfIteration;
    }

    public void setNumberOfIteration(int numberOfIteration) {
        this.numberOfIteration = numberOfIteration;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
