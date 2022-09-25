package com.example.ssn_api.ssn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SSNService {
    private SSN ssn;

    public ResponseEntity<Object> ssnValidate(SSN ssn){
        return new ResponseEntity<>("okay", HttpStatus.OK);
    }
}
