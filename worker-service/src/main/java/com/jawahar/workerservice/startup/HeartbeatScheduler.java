package com.jawahar.workerservice.startup;

import com.jawahar.workerservice.dto.HeartbeatResponse;
import com.jawahar.workerservice.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "worker.heartbeat.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class HeartbeatScheduler {

    @Autowired
    private HeartbeatService heartbeatService;

    @Scheduled(initialDelay = 30000,fixedRate = 30000)
    public void heartbeat() {
        System.out.println();
        System.out.println("--------------- HEARTBEAT ----------------");

        HeartbeatResponse response = heartbeatService.sendHeartbeat();

        System.out.println("Worker ID      : " + response.getWorkerId());
        System.out.println("Worker Status  : " + response.getStatus());
        System.out.println("Last Heartbeat : " + response.getLastHeartbeat());

        System.out.println("------------------------------------------");
        System.out.println();
    }
}
