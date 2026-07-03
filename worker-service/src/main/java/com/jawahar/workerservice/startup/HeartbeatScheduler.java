package com.jawahar.workerservice.startup;

import com.jawahar.workerservice.dto.HeartbeatResponse;
import com.jawahar.workerservice.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HeartbeatScheduler {

    @Autowired
    private HeartbeatService heartbeatService;

    @Scheduled(initialDelay = 30000,fixedRate = 30000)
    public void heartbeat() {
        System.out.println("Sending heartbeat...");

        HeartbeatResponse response = heartbeatService.sendHeartbeat();

        System.out.println("Heartbeat sent.");
        System.out.println(response);
    }
}
