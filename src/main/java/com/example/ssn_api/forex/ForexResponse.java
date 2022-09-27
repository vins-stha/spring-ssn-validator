package com.example.ssn_api.forex;

public class ForexResponse {
    private ForexRequestModel requestModel;
    private float exchange_rate;

    public ForexRequestModel getRequestModel() {
        return requestModel;
    }

    public ForexResponse() {
    }

    public void setRequestModel(ForexRequestModel requestModel) {
        this.requestModel = requestModel;
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
                "requestModel=" + requestModel.toString() +
                ", exchange_rate=" + exchange_rate +
                '}';
    }
}
