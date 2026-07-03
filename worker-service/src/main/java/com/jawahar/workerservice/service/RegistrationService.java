package com.jawahar.workerservice.service;

import com.jawahar.workerservice.config.WorkerContext;
import com.jawahar.workerservice.dto.RegisterWorkerRequest;
import com.jawahar.workerservice.dto.RegisterWorkerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RegistrationService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private WorkerContext workerContext;

    public RegisterWorkerResponse registerWorker() {

        RegisterWorkerRequest request = RegisterWorkerRequest.builder()
                .workerName("worker-1")
                .host("localhost")
                .port(8081)
                .maxCapacity(5)
                .build();

        RegisterWorkerResponse response = restClient.post()
                .uri("http://localhost:8080/workers/register")
                .body(request)
                .retrieve()
                .body(RegisterWorkerResponse.class);

        // Store the worker information for future heartbeats
        workerContext.setWorkerId(response.getWorkerId());
        workerContext.setWorkerName(request.getWorkerName());
        System.out.println("Stored WorkerContext ID = " + workerContext.getWorkerId());

        return response;
    }
}