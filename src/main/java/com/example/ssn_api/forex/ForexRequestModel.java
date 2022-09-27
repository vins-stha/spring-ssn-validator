package com.example.ssn_api.forex;

import org.springframework.beans.factory.annotation.Value;

public class ForexRequestModel {
    private String from;
    private String to;
    private float to_amount;
//    @Autowired
//    @Autowired

//    @Value("${forex.api_key}")
//    private String apikey;

    public ForexRequestModel (){
    };

    public ForexRequestModel(String from, String to, float to_amount) {
        this.from = from;
        this.to = to;
        this.to_amount = to_amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getTo_amount() {
        return to_amount;
    }

    public void setTo_amount(float to_amount) {
        this.to_amount = to_amount;
    }

    @Override
    public String toString() {
        return "ForexRequestModel{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", to_amount=" + to_amount +
                '}';
    }

}

