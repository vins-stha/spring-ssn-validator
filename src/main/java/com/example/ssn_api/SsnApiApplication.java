package com.example.ssn_api;


import com.example.ssn_api.forex.ForexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SsnApiApplication {
//    @Autowired
//    static
//    ForexController forexController;

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    public static void main(String[] args)  {

        SpringApplication.run(SsnApiApplication.class, args);
//        forexController.startScheduledJob();
    }

}
