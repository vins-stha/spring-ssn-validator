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
        try {
            JobDetail jobDetail = jobDetail();
            Trigger trigger = buildTrigger(jobDetail);

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException exception) {
            System.out.println("Error occurred while scheduling the job. Message= \n" + exception.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private JobDetail jobDetail() {
        return JobBuilder.newJob(FetchApiJob.class)
                .withIdentity(UUID.randomUUID().toString(), "forexfetchapijobs")
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
                                .withIntervalInHours(1)
                                .repeatForever()
                                .withMisfireHandlingInstructionFireNow()
                )
                .build();
    }
}
