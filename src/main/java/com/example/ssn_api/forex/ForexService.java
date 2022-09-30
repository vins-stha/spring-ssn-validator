package com.example.ssn_api.forex;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ForexService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private Environment env;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    ForexRepository forexRepository;

    public ForexService() {
    }

    @Cacheable(value = "rate")
    public float getExchangeRate(String from, String to) {
        float rate = 0;
        float amount = 124;
        if (forexRepository.getExchangeRate(from, to) == null) {
            String apiResult = webClientBuilder.build()
                    .get()
                    .uri("https://api.apilayer.com/exchangerates_data/convert?to=" +
                            to
                            + "&from=" + from
                            + "&amount=" + amount)
                    .header("apikey", env.getProperty("forex.api_key"))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            try {
                JSONObject jsonObject = new JSONObject(apiResult);
                JSONObject infoData = jsonObject.getJSONObject("info");
                rate = Float.parseFloat(String.valueOf(infoData.get("rate")));
                ForexEntity entity = new ForexEntity(from, to, rate);
                forexRepository.save(entity);
                return rate;

            } catch (Exception e) {
                System.out.println("Exception occured" + e.toString());
                e.printStackTrace();
            }
        } else
            rate = forexRepository.getExchangeRate(from, to);

        return rate;
    }
}
