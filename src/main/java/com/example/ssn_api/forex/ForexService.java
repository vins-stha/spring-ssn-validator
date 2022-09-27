package com.example.ssn_api.forex;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ForexService {
    // private final WebClient
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private Environment env;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    ForexRepository forexRepository;

    @Cacheable(value = "rate", key = "{#From, #To}")
    public Float getExchangeRate(String From, String To) {
        float rate = 0;
        float amount = 124;
        rate = forexRepository.getExchangeRate(From, To);
        System.err.println("RATE saved = " + rate);
        Cache cr_rate = cacheManager.getCache("rate");

        System.out.println("CACHE=" + cr_rate.getClass() + cr_rate.get("{#From, #To}"));
        String apiResult = webClientBuilder.build()
                .get()
                .uri("https://api.apilayer.com/exchangerates_data/convert?to=" +
                        To
                        + "&from=" + From
                        + "&amount=" + amount)
                .header("apikey", env.getProperty("forex.api_key"))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JSONObject jsonObject = new JSONObject(apiResult);
            JSONObject infoData = jsonObject.getJSONObject("info");
            rate = Float.parseFloat(String.valueOf(infoData.get("rate")));

            return rate;

        } catch (Exception e) {
            System.out.println("Exception occured" + e.toString());
            e.printStackTrace();
        }
        return rate;
    }
}
