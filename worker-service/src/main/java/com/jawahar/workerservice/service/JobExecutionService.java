package com.jawahar.workerservice.service;

import com.jawahar.workerservice.config.WorkerContext;
import com.jawahar.workerservice.dto.NextJobResponse;
import com.jawahar.workerservice.dto.UpdateJobStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class JobExecutionService {

    @Autowired
    private WorkerContext workerContext;

    @Autowired
    private RestClient restClient;

    public void executeJob(NextJobResponse job){

        try {

            System.out.println();
            System.out.println("======================================================");
            System.out.println("                 JOB EXECUTION");
            System.out.println("======================================================");
            System.out.println("Job ID        : " + job.getJobId());
            System.out.println("Worker        : " + workerContext.getWorkerName());
            System.out.println("Execution     : STARTED");
            System.out.println("======================================================");

            // Simulate execution
            Thread.sleep(3000);

            // Simulate random success/failure
            boolean success = Math.random() > 0.3;

            System.out.println("Execution     : COMPLETED");
            System.out.println("Final Status  : " + (success ? "SUCCESS" : "FAILED"));
            System.out.println("======================================================");
            System.out.println();

            UpdateJobStatusRequest request =
                    UpdateJobStatusRequest.builder()
                            .jobId(job.getJobId())
                            .workerId(workerContext.getWorkerId())
                            .status(success ? "SUCCESS" : "FAILED")
                            .build();

            restClient.post()
                    .uri("http://localhost:8080/jobs/status")
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();

        } catch (Exception e) {

            System.err.println("Job Execution Error");


        }
    }
}
