package com.example.ssn_api.ssn;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class SSN {

    private String SSN;
    private String CountryCode;


    public SSN(String SSN, String CountryCode) {
        this.CountryCode = CountryCode;
        this.SSN = SSN;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    @Override
    public String toString() {
        return "SSN{" +
                "CountryCode='" + CountryCode + '\'' +
                ", SSN='" + SSN + '\'' +
                '}';
    }
}

