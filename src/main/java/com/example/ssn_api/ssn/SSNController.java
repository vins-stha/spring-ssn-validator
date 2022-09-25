package com.example.ssn_api.ssn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")

public class SSNController {
    @GetMapping(value = "/ssnValidate")
    public ResponseEntity<Object> validate() {
        System.out.println("hello world");
        return new ResponseEntity<>("ssn", HttpStatus.OK);
    }

    @PostMapping(value = "/ssnValidate")
    public ResponseEntity<Object> validateSSN(@RequestBody SSN ssn) {
        if (!ssn.getCountryCode().toUpperCase().equals("FI")) {
            throw new ArithmeticException("Country not found");
        }
        System.out.println(ssn.getSSN());
        System.out.println(ssn.getCountryCode());

        // Phase - 1
        Pattern pattern = Pattern.compile("[0-3][0-9][0-1][1-9][0-9][0-9][-,+,A][002-899]\\d\\d[A-F,H,J-N,P,R-Y,0-9]");
        Matcher matcher = pattern.matcher(ssn.getSSN());

        // Phase - I check pattern
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

            // Check if date is valid
            if (isDateValid(dm + uuuu)) {

                // Check if PIC is valid
                if (isPICValid((dm + uu + PIC), ctrlchar))
                    System.out.println("Is valid SSN");
                else
                    System.out.println("Not valid SSN");
            }

        }

        String[] dates = {"21081989", "32082090", "32321999", "27021921", "04092022"};
        for (String date : dates) {
            System.out.println(date + "isValid=" + isDateValid(date));
        }

        return new ResponseEntity<>(ssn.toString(), HttpStatus.OK);
    }

    public boolean isDateValid(String date) {
        boolean result = false;
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("ddMMuuuu").withResolverStyle(ResolverStyle.STRICT));
            result = true;

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            result = false;
        }
        return result;

    }

    public boolean isPICValid(String nums, String pic) {
        String[] pics = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "H", "I", "J", "K", "L",
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
