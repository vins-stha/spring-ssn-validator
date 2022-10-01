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
    public float getExchangeRate(String from, String to) throws InterruptedException {
        Thread.sleep(5000);
        return forexRepository.getExchangeRate(from, to);
    }

    public void fetchExchangeRateFromApi(String from, String to) {
        try {
            float rate = getRateFromApi(from, to);

            if (forexRepository.getExchangeRate(from, to) == null) {

                System.out.println("Creating new record for pair " + from + "-" + to);

                ForexEntity entity = new ForexEntity(from, to, rate);
                forexRepository.save(entity);

            } else {
                // Update rate
                System.out.println("Updating rate data for record for pair " + from + "-" + to);

                ForexEntity entity = forexRepository.getExchangeEntity(from, to);
                entity.setRate(rate);

                forexRepository.save(entity);
            }
        } catch (Exception exception) {
            System.out.println("Exception occurred " + exception.getMessage());
            exception.printStackTrace();

        }
    }

    private float getRateFromApi(String from, String to) {
        float amount = 1;
        float rate = 0;
        try {
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

            JSONObject jsonObject = new JSONObject(apiResult);
            JSONObject infoData = jsonObject.getJSONObject("info");

            return Float.parseFloat(String.valueOf(infoData.get("rate")));

        } catch (Exception e) {
            System.out.println("Exception occurred while fetching from api" + e.toString());
            e.printStackTrace();
        }
        return rate;
    }
}
