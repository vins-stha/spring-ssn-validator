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
        CURRENCIES.add("EUR");
        CURRENCIES.add("SEK");
        CURRENCIES.add("USD");
    }

    public ArrayList<String> getCURRENCIES() {
        return CURRENCIES;
    }

    public FetchApiJob(ForexService forexService) {
        this.forexService = forexService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("Handling schedulded jobs...");

        for(String from: CURRENCIES){
            for(String to: CURRENCIES) {
                System.out.println("from" + to + "from" + from);
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
