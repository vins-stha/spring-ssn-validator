package com.example.ssn_api.forex;

public class ForexResponse {
    private ForexRequest forexRequest;
    private float exchange_rate;

    public ForexRequest getRequestModel() {
        return forexRequest;
    }

    public ForexResponse() {
    }

    public void setRequestModel(ForexRequest forexRequest) {
        this.forexRequest = forexRequest;
    }

    public float getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(float exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    @Override
    public String toString() {
        return "ForexResponse{" +
                "requestModel=" + forexRequest.toString() +
                ", exchange_rate=" + exchange_rate +
                '}';
    }
}
