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
    @PostMapping(value = "/forex")
    public ResponseEntity<Object> convert() {
        System.out.println("hello world");
        return new ResponseEntity<>("It works!", HttpStatus.OK);
    }
 // API KEY = ecwLNPhmvfgUrPOADjo1lFkKqNxyBu


 @GetMapping(value = "/forex")
//    public Mono<ForexResponse> convertForex(@RequestBody ForexRequestModel forexObject) { }
     public ResponseEntity<Object> convertForex(@RequestBody ForexRequestModel forexObject) {

         System.out.println("It is working apikey=" );
        ForexRequestModel myobj = new ForexRequestModel();
//     System.out.println("key=" +  myobj.getApikey());
//     Mono<ForexRequestModel> data = webClientBuilder.build()
//             .get()
//             .uri("https://api.apilayer.com/exchangerates_data/convert?to=USD&from=EUR&amount=125")
//             .header("apikey", "ecwLNPhmvfgUrPOADjo1lFkKqNxyBufT")
//             .retrieve()
//             .bodyToMono(ForexRequestModel.class).log();

 String dataR = webClientBuilder.build()
             .get()
             .uri("https://api.apilayer.com/exchangerates_data/convert?to=USD&from=EUR&amount=125")
             .header("apikey", "ecwLNPhmvfgUrPOADjo1lFkKqNxyBufT")
             .retrieve()
             .bodyToMono(String.class).block();

     ObjectMapper mapper = new ObjectMapper();

     try {
         Map<String, Object> userData = mapper.readValue(
                 dataR, new TypeReference<Map<String, Object>>() {
                 });
         Map map = new ObjectMapper().readValue(dataR, HashMap.class);

         HashMap<String, Object> parsedData = new ObjectMapper().readValue(dataR, HashMap.class);
         System.out.println("Parsed data " + parsedData.get("info"));

         JSONObject jsonObject = new JSONObject(dataR);
         System.out.println("jsonObj=" + jsonObject);
         JSONObject infoData = jsonObject.getJSONObject("info");
         System.out.println("infodata=" + infoData);
         Object rate= infoData.get("rate");

         System.out.println("rate=" + rate);
//         List<Map> infoData = (List<Map>) parsedData.get("info");
//         System.out.println("infodta" + infoData);

//         List<Map> info = (List) parsedData.get("info");
//         System.out.println("INFO" +  info);

//         System.out.println("map" + map);
//         for  (Object entry : map.entrySet())
//             System.out.println("Key = " + entry);


//         String obj = userData.get("query").toString();

//         Map<String, Object> queryData = mapper.readValue(
//                 userData.get("query").toString(), new TypeReference<Map<String, Object>>() {
//                 });

//         System.out.println("Results" + userData);
//
//         System.out.println("query" + (userData.get("query")));
//         System.out.println("class" + userData.get("query"));
//         System.out.println("str representation" +queryData);
//         System.out.println("String obj" + queryData.get("from"));



     }
     catch (Exception e) {
         System.out.println("Exception occured" + e.toString());
         e.printStackTrace();


     }
//
//        ForexRequestModel dataF = data.block();

     JSONObject finalResponse = new JSONObject();

     finalResponse.put("from","");
     finalResponse.put("to", "");
     finalResponse.put("to_amount", "");
     finalResponse.put("exchange_rate","");

     // save to database rate

//     System.out.println(dataF.getDate());
//     System.out.println(dataF.getResult());
//     System.out.println(dataF.getInformationObject());
//     System.out.println(dataF.getQueryObject());


     return new ResponseEntity<>( dataR, HttpStatus.OK);
    }
}
