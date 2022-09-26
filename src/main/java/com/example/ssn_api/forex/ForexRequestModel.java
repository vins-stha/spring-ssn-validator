package com.example.ssn_api.forex;

import org.springframework.beans.factory.annotation.Value;

public class ForexRequestModel {
    private boolean success;
    private Query query;
    private Info info;
    private String date;
    private boolean history;
    private float result;
//    @Autowired
//    @Autowired

//    @Value("${forex.api_key}")
//    private String apikey;

    public ForexRequestModel (){
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Query getQueryObject() {
        return query;
    }

    public void setQueryObject(Query queryObject) {
        this.query = queryObject;
    }

    public Info getInformationObject() {
        return info;
    }

    public void setInformationObject(Info informationObject) {
        this.info = informationObject;
    }

//    public String getApikey() {
//        return apikey;
//    }
//
//    public void setApikey(String apikey) {
//        this.apikey = apikey;
//    }

    @Override
    public String toString() {
        return "ForexRequestModel{" +
                "date='" + date + '\'' +
                ", history=" + history +
                ", result=" + result +
                ", success=" + success +
                ", queryObject=" + query +
                ", informationObject=" + info +
//                ", apikey='" + apikey + '\'' +
                '}';
    }
}

