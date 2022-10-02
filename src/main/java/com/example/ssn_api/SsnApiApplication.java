package com.example.ssn_api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SsnApiApplication {
    public static String[] SUPPORTED_CURRENCIES = {"SEK", "EUR", "USD"};

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    public static void main(String[] args)  {

        SpringApplication.run(SsnApiApplication.class, args);
    }

    public static List<String> getSupportedCurrenceis(){
      return Arrays.asList(SsnApiApplication.SUPPORTED_CURRENCIES);
    }


}
