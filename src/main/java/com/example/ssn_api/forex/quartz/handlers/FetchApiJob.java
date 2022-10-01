package com.example.ssn_api.forex.quartz.handlers;

import com.example.ssn_api.SsnApiApplication;
import com.example.ssn_api.forex.ForexService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

// Step 2
public class FetchApiJob extends QuartzJobBean {

    @Autowired
    private ForexService forexService;

    public FetchApiJob() {
    }

    public FetchApiJob(ForexService forexService) {
        this.forexService = forexService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        List<String> supportedCurrencies =  SsnApiApplication.getSupportedCurrenceis();

        for(String from: supportedCurrencies){
            for(String to: supportedCurrencies) {
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
