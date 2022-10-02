package com.example.ssn_api.forex;


import com.example.ssn_api.SsnApiApplication;
import com.example.ssn_api.forex.quartz.handlers.QuartzSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ForexController {

    // Step 4
    @Autowired
    private QuartzSchedulerService quartzSchedulerService;

    public ForexController() {
    }

    @Autowired
    private ForexService forexService;

    @PostMapping(value = "/forex")
    public ResponseEntity<Object> convert() {
        return new ResponseEntity<>("It works!", HttpStatus.OK);
    }

    @GetMapping(value = "/forex")
    public Map<String, Object> convertForex(@RequestBody ForexRequest requestObject) {
        Map<String, Object> finalResponse = new HashMap<>();

        List<String> supportedCurrencies =  SsnApiApplication.getSupportedCurrenceis();

        if (!supportedCurrencies.contains(requestObject.getFrom()) || !supportedCurrencies.contains(requestObject.getTo())) {

            throw new RuntimeException("Currency not supported currently");
        }

        try {
            finalResponse.put("from", requestObject.getFrom());
            finalResponse.put("to", requestObject.getTo());
            finalResponse.put("to_amount", requestObject.getTo_amount());
            finalResponse.put("exchange_rate",
                    forexService.getExchangeRate(requestObject.getFrom(), requestObject.getTo()));
            return finalResponse;

        } catch (Exception e) {
            System.out.println("Exception occurred" + e.toString());
            e.printStackTrace();
        }

        return finalResponse;
    }

    // Step 5
    @GetMapping("/startJob")
//    @EventListener(ApplicationReadyEvent.class)

    public void startScheduledJob() {
        quartzSchedulerService.startScheduledJob();
    }
}
