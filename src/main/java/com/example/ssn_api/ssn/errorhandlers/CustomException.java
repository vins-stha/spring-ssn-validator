package com.example.ssn_api.ssn.errorhandlers;

public class CustomException extends RuntimeException{
    public CustomException (String message) {
        super(message);
    }
}
