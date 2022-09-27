package com.example.ssn_api.forex;

//
//import org.json.JSONArray;
//import org.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class ForexController {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private Environment env;

    @PostMapping(value = "/forex")
    public ResponseEntity<Object> convert() {
        System.out.println("hello world");
        return new ResponseEntity<>("It works!", HttpStatus.OK);
    }

    @GetMapping(value = "/forex")
    public Map<String, Object> convertForex(@RequestBody ForexRequestModel requestObject) {
        String apiResult = webClientBuilder.build()
                .get()
                .uri("https://api.apilayer.com/exchangerates_data/convert?to=" +
                        requestObject.getTo()
                        + "&from=" + requestObject.getFrom()
                        + "&amount=" + requestObject.getTo_amount())
                .header("apikey", env.getProperty("forex.api_key"))
                .retrieve()
                .bodyToMono(String.class).block();

        Map<String, Object> finalResponse = new HashMap<>();

        try {
            JSONObject jsonObject = new JSONObject(apiResult);
            JSONObject infoData = jsonObject.getJSONObject("info");

            finalResponse.put("from", requestObject.getFrom());
            finalResponse.put("to", requestObject.getTo());
            finalResponse.put("to_amount", requestObject.getTo_amount());
            finalResponse.put("exchange_rate", Float.parseFloat(String.valueOf(infoData.get("rate"))));

            return finalResponse;

        } catch (Exception e) {
            System.out.println("Exception occured" + e.toString());
            e.printStackTrace();
        }

        return finalResponse;
    }
}
