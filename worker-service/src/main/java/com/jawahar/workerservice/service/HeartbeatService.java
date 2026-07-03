package com.jawahar.workerservice.service;

import com.jawahar.workerservice.config.WorkerContext;
import com.jawahar.workerservice.dto.HeartbeatRequest;
import com.jawahar.workerservice.dto.HeartbeatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HeartbeatService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private WorkerContext workerContext;

    public HeartbeatResponse sendHeartbeat() {

        System.out.println("Heartbeat Worker ID = " + workerContext.getWorkerId());

        if (workerContext.getWorkerId() == null) {
            System.out.println("Worker is not registered yet. Skipping heartbeat.");
            return null;
        }

        HeartbeatRequest request = HeartbeatRequest.builder()
                .workerId(workerContext.getWorkerId())
                .build();

        return restClient.post()
                .uri("http://localhost:8080/workers/heartbeat")
                .body(request)
                .retrieve()
                .body(HeartbeatResponse.class);
    }
}