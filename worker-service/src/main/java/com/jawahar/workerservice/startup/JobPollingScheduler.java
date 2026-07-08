package com.jawahar.workerservice.startup;

import com.jawahar.workerservice.service.JobPollingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobPollingScheduler {

    @Autowired
    private JobPollingService jobPollingService;

    @Scheduled(initialDelay = 10000, fixedRate = 5000)
    public void pollJob() {

        jobPollingService.pollForJob();

    }
}
