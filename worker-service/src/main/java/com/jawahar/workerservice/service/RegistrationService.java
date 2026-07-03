package com.jawahar.workerservice.service;

import com.jawahar.workerservice.dto.RegisterWorkerRequest;
import com.jawahar.workerservice.dto.RegisterWorkerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RegistrationService {

    @Autowired
    private RestClient restClient;

    public RegisterWorkerResponse registerWorker(){

        RegisterWorkerRequest request= RegisterWorkerRequest.builder()
                .workerName("worker-1")
                .host("localhost")
                .port(8081)
                .maxCapacity(5)
                .build();

        return restClient.post()
                .uri("http://localhost:8080/workers/register")
                .body(request)
                .retrieve()
                .body(RegisterWorkerResponse.class);
    }
}
