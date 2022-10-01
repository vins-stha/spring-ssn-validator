package com.example.ssn_api.forex.quartz.handlers;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuartzSchedulerService {
    @Autowired
    private Scheduler scheduler;

    public QuartzSchedulerService() {
    }

    public ResponseEntity<Void> startScheduledJob() {
        System.out.println("Called starter schedulere job funciotn");
        try {
            JobDetail jobDetail = jobDetail();
            System.out.println("JobDetail =>" + jobDetail.toString());
            Trigger trigger = buildTrigger(jobDetail);
            System.out.println("trigger =>" + trigger.toString());

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException exception) {
            System.out.println("Error occured while scheduling the job. Message= \n" + exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    private JobDetail jobDetail() {
        System.out.println("Called jobdetail funciton");
        return JobBuilder.newJob(FetchApiJob.class)
                .withIdentity(UUID.randomUUID().toString(), "forexfetchapijobs")
                .storeDurably()
                .build();
    }

    // Step 3
    private Trigger buildTrigger(JobDetail jobDetail) {
        System.out.println("Called build trigger funciton");

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().toString(), "forexfetchapijobs")
                .withDescription("Fetch api triggers")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(120)
                                .withRepeatCount(2)
                                .withMisfireHandlingInstructionFireNow()
                )

                .build();
    }
}
