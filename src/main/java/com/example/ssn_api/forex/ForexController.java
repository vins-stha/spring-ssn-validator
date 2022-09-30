package com.example.ssn_api.forex;

// import org.json.JSONObject;

import com.example.ssn_api.forex.quartz.handlers.FetchApiJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ForexController {

    // Step 4
    @Autowired
    private Scheduler scheduler;

    public ForexController() {
    }

    @Autowired
    private ForexService forexService;

    @PostMapping(value = "/forex")
    public ResponseEntity<Object> convert() {
        System.out.println("hello world");
        return new ResponseEntity<>("It works!", HttpStatus.OK);
    }

    @GetMapping(value = "/forex")
    public Map<String, Object> convertForex(@RequestBody ForexRequestModel requestObject) {
        Map<String, Object> finalResponse = new HashMap<>();

        try {
            finalResponse.put("from", requestObject.getFrom());
            finalResponse.put("to", requestObject.getTo());
            finalResponse.put("to_amount", requestObject.getTo_amount());
            FetchApiJob job = new FetchApiJob();

            // System.out
            // .println("Rate = " + forexService.getExchangeRate(requestObject.getFrom(),
            // requestObject.getTo()));
//            System.out.println("Fetched exchange rate =" + forexService.getExchangeRate(requestObject.getFrom(), requestObject.getTo()));
            finalResponse.put("exchange_rate",
                    forexService.getExchangeRate(requestObject.getFrom(), requestObject.getTo()));
//            ForexEntity entity = forexRepository.getExchangeEntity();
            return finalResponse;

        } catch (Exception e) {
            System.out.println("Exception occured" + e.toString());
            e.printStackTrace();
        }

        return finalResponse;
    }

    // Step 5
    @GetMapping("/startJob")
    public ResponseEntity<Void> startScheduledJob() {
        try {
            System.out.println("Sending the schedluing job now...");
            JobDetail jobDetail = jobDetail();
            Trigger trigger = buildTrigger(jobDetail);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException exception) {
            System.out.println("Error occured while scheduling the job. Message= \n" + exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Step 1
    private JobDetail jobDetail() {
        return JobBuilder.newJob(FetchApiJob.class)
                .withIdentity(UUID.randomUUID().toString(), "forexfetchapijobs")
//                .usingJobData(jobData)
                .storeDurably()
                .build();
    }

    // Step 3
    private Trigger buildTrigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().toString(), "forexfetchapijobs")
                .withDescription("Fetch api triggers")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(60)
                                .withRepeatCount(2)
                                .withMisfireHandlingInstructionFireNow()
                )

                .build();
    }


}
