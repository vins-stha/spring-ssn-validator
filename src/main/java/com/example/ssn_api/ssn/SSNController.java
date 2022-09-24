package com.example.ssn_api.ssn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class SSNController {
    @GetMapping(value = "/ssnValidate")
    public ResponseEntity<Object>validate(){
        System.out.println("hello world");
        return new ResponseEntity<>("ssn", HttpStatus.OK);

    }

    @PostMapping(value = "/ssnValidate")
    public ResponseEntity<Object>validateSSN(@RequestBody SSN ssn){
        System.out.println(ssn.getSSN());
        System.out.println(ssn.getCountryCode());
        // Phase - 1
        Pattern pattern = Pattern.compile("[0-3][0-9][0-1][1-9][0-9][0-9][-,+,A][002-899]\\d\\d[A-F,H,J-N,P,R-Y,0-9]");
        Matcher matcher = pattern.matcher(ssn.getSSN());
        if(matcher.find()){
            // Phase 2
        }

        return new ResponseEntity<>(ssn.toString(), HttpStatus.OK);

    }

    public boolean isValid(String type, int num){
        boolean result = true;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMYY", Locale.US)
                .withResolverStyle(ResolverStyle.STRICT);
        DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

        if (type.equals("date"))
        {
//            result =
        }

        return result;

    }
}
