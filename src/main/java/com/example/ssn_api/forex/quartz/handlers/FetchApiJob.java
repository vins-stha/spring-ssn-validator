package com.example.ssn_api.forex.quartz.handlers;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// Step 2
public class FetchApiJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("Handling schedulded jobs...");
        printHelloJob();
    }

    public void printHelloJob()
    {
        System.out.println("Hellow world");
    }
}
