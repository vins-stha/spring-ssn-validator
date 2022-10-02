package com.example.ssn_api.ssn;

import com.example.ssn_api.errorhandlers.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")

public class SSNController {
    @GetMapping(value = "/validateSSN")
    public ResponseEntity<Object> validate() {
        System.out.println("hello world");
        return new ResponseEntity<>("It works!", HttpStatus.OK);
    }

    @PostMapping(value = "/validateSSN")
    public ResponseEntity<Object> validateSSN(@RequestBody SSN ssn) {
        boolean result = false;

        if (!ssn.getCountryCode().toUpperCase().equals("FI")) {
            throw new CustomException("Country not supprted");
        }

        Map<String, Object> finalResponse = new HashMap<>();

        // Phase - 1
        Pattern pattern = Pattern.compile("[0-3][0-9][0-1][0-9][0-9][0-9][-,+,A][002-899]\\d\\d[A-F,H,J-N,P,R-Y,0-9]");
        Matcher matcher = pattern.matcher(ssn.getSSN());

        if (matcher.find()) {
            String dm = ssn.getSSN().substring(0, 4);
            String uu = ssn.getSSN().substring(4, 6);
            String separator = ssn.getSSN().substring(6, 7);
            String PIC = ssn.getSSN().substring(7, 10);
            String ctrlchar = ssn.getSSN().substring(10);
            String uuuu = "";

            if (separator.equals("-"))
                uuuu = "19" + uu;
            if (separator.equals("+"))
                uuuu = "18" + uu;
            if (separator.equals("A"))
                uuuu = "20" + uu;

            if (isDateValid(dm + uuuu)) {
                // Check if PIC is valid
                if (isPICValid((dm + uu + PIC), ctrlchar)) {
                    result = true;
                }
                else
                    System.out.println("Invalid PIC");
            }
            else
                System.out.println("Invalid date");
        }
        else
            System.out.println("Invalid pattern");

        finalResponse.put("ssn_valid", result);

        return new ResponseEntity<>(finalResponse, HttpStatus.OK);
    }

    public boolean isDateValid(String date) {
        boolean result = false;
        try {
            LocalDate
                    .parse(date, DateTimeFormatter.ofPattern("ddMMuuuu")
                            .withResolverStyle(ResolverStyle.STRICT));
            result = true;

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return result;
    }

    public boolean isPICValid(String nums, String pic) {
        String[] pics = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "H", "J", "K", "L",
                "M", "N", "P", "R", "S", "T", "U", "V", "W",
                "X", "Y"};

        double PIC = Double.parseDouble(nums);
        double divResult = PIC / 31;
        int longPart = (int) divResult;
        int res = Integer.valueOf(String.valueOf(Math.round((divResult - longPart) * 31)));

        if (pics[res].equals(pic))
            return true;
        else
            return false;
    }
}
