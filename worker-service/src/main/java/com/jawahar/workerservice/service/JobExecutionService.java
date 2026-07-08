package com.jawahar.workerservice.service;

import com.jawahar.workerservice.config.WorkerContext;
import com.jawahar.workerservice.dto.CompleteJobRequest;
import com.jawahar.workerservice.dto.NextJobResponse;
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

        try{

            System.out.println("--------------------------------");
            System.out.println("Executing Job...");
            System.out.println(job.getJobId());

            Thread.sleep(3000);

            // Simulate failure randomly
            boolean success = Math.random() > 0.3;

            System.out.println("Execution Completed");

            CompleteJobRequest request =
                    CompleteJobRequest.builder()
                            .jobId(job.getJobId())
                            .workerId(workerContext.getWorkerId())
                            .status(success ? "SUCCESS" : "FAILED")
                            .build();
            System.out.println(
                    success
                            ? "Job Executed Successfully"
                            : "Job Execution Failed");

            restClient.post()
                    .uri("http://localhost:8080/jobs/complete")
                    .body(request)
                    .retrieve()
                    .toBodilessEntity();

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
