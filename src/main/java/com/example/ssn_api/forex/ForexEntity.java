package com.example.ssn_api.forex;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "forex_rates")
public class ForexEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from")
    private String from;
    @Column(name = "to")
    private String to;
    @Column(name = "rate")
    private float rate;

    public ForexEntity() {
    };

    public ForexEntity(String from, String to, float rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ForexRequestModel{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", rate=" + rate +
                '}';
    }

}
