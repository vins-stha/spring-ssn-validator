package com.example.ssn_api.forex.quartz.handlers;

import com.example.ssn_api.forex.ForexService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;

// Step 2
public class FetchApiJob extends QuartzJobBean {

    @Autowired
    private ForexService forexService;

    ArrayList<String> CURRENCIES = new ArrayList<String>();

    public FetchApiJob() {
    }

    public ArrayList<String> getCURRENCIES() {
        return CURRENCIES;
    }
    public void setCURRENCIES(ArrayList<String> supportedCurrencies) {
        this.CURRENCIES = supportedCurrencies;
    }

    public FetchApiJob(ForexService forexService) {
        this.forexService = forexService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        ArrayList<String> allowedCurrencies = new ArrayList<String>();
        allowedCurrencies.add("EUR");
        allowedCurrencies.add("SEK");
        allowedCurrencies.add("USD");
        setCURRENCIES(allowedCurrencies);
        System.out.println("Allowed currencies " + CURRENCIES);
        for(String from: CURRENCIES){
            for(String to: CURRENCIES) {
                forexService.fetchExchangeRateFromApi(from,to);
            }
        }

        printHelloJob();

    }

    public void printHelloJob()
    {
        System.out.println("Hellow world");
    }
}
