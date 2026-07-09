package com.jawahar.workerservice.service;

import com.jawahar.workerservice.config.WorkerContext;
import com.jawahar.workerservice.dto.NextJobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class JobPollingService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private WorkerContext workerContext;

    @Autowired
    private JobExecutionService jobExecutionService;

    public void pollForJob() {

        if (workerContext.getWorkerId() == null) {
            return;
        }

        ResponseEntity<NextJobResponse> response = restClient.get()
                .uri("http://localhost:8080/jobs/worker/{workerId}/next",
                        workerContext.getWorkerId())
                .retrieve()
                .toEntity(NextJobResponse.class);

        if (response.getStatusCode().is2xxSuccessful()
                && response.getBody() != null) {

            NextJobResponse job = response.getBody();


            jobExecutionService.executeJob(job);
            System.out.println();
            System.out.println("======================================================");
            System.out.println("                JOB RECEIVED");
            System.out.println("======================================================");
            System.out.println("Job ID      : " + job.getJobId());
            System.out.println("Job Type    : " + job.getJobType());
            System.out.println("Status      : " + job.getStatus());
            System.out.println("Payload     :");
            System.out.println(job.getPayload());
            System.out.println("======================================================");
            System.out.println();
        }
    }
}
