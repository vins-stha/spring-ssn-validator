package com.example.ssn_api.forex;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ForexController {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private Environment env;
    @Autowired
    private ForexService forexService;

    @PostMapping(value = "/forex")
    public ResponseEntity<Object> convert() {
        System.out.println("hello world");
        return new ResponseEntity<>("It works!", HttpStatus.OK);
    }

    @GetMapping(value = "/forex")
    public Map<String, Object> convertForex(@RequestBody ForexRequestModel requestObject) {
        // String apiResult = webClientBuilder.build()
        // .get()
        // .uri("https://api.apilayer.com/exchangerates_data/convert?to=" +
        // requestObject.getTo()
        // + "&from=" + requestObject.getFrom()
        // + "&amount=" + requestObject.getTo_amount())
        // .header("apikey", env.getProperty("forex.api_key"))
        // .retrieve()
        // .bodyToMono(String.class).block();

        Map<String, Object> finalResponse = new HashMap<>();

        try {
            // JSONObject jsonObject = new JSONObject(apiResult);
            // JSONObject infoData = jsonObject.getJSONObject("info");

            finalResponse.put("from", requestObject.getFrom());
            finalResponse.put("to", requestObject.getTo());
            finalResponse.put("to_amount", requestObject.getTo_amount());
            finalResponse.put("exchange_rate",
                    forexService.getExchangeRate(requestObject.getFrom(), requestObject.getTo()));
            // finalResponse.put("exchange_rate",
            // Float.parseFloat(String.valueOf(infoData.get("rate"))));

            return finalResponse;

        } catch (Exception e) {
            System.out.println("Exception occured" + e.toString());
            e.printStackTrace();
        }

        return finalResponse;
    }
}
