package com.example.ssn_api.forex;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Query {
    private float amount;
    private String from;
    @JsonProperty("to")
    public String myto;
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String currencyFrom) {
        this.from = currencyFrom;
    }

    public String getTo() {
        return myto;
    }

    public void setTo(String getCurrencyTo) {
        this.myto = getCurrencyTo;
    }


}
