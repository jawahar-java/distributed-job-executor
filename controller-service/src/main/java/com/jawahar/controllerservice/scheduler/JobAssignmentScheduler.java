package com.jawahar.controllerservice.scheduler;

import com.jawahar.controllerservice.service.JobAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobAssignmentScheduler {

    @Autowired
    private JobAssignmentService jobAssignmentService;

    @Scheduled(fixedRate = 5000)
    public void assignJobs() {

        jobAssignmentService.assignJobs();

    }
}
