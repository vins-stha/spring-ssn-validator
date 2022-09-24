package com.example.ssn_api.ssn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            System.out.println("DATE=>" + ssn.getSSN().substring(0,5));
        }

        String [] dates = {"210889","320890","32321999","270221", "040922"};
        for(String date:dates){
            System.out.println(date + "isValid=" + isValid(date));
        }

        return new ResponseEntity<>(ssn.toString(), HttpStatus.OK);
    }

    public boolean isValid( String date){
        boolean result = false;
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMuu").withResolverStyle(ResolverStyle.STRICT));
            result = true;

        }catch(DateTimeParseException e){
            e.printStackTrace();
            result = false;
        }
        return result;

    }
}
