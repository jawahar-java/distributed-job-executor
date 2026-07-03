package com.jawahar.controllerservice.controller;

import com.jawahar.controllerservice.dto.HeartbeatRequest;
import com.jawahar.controllerservice.dto.HeartbeatResponse;
import com.jawahar.controllerservice.dto.RegisterWorkerRequest;
import com.jawahar.controllerservice.dto.RegisterWorkerResponse;
import com.jawahar.controllerservice.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/workers")
@RestController
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("/register")
    public ResponseEntity<RegisterWorkerResponse> registerWorker(
            @Valid @RequestBody RegisterWorkerRequest request) {

        return ResponseEntity.ok(workerService.registerWorker(request));
    }

    @PostMapping("/heartbeat")
    public ResponseEntity<HeartbeatResponse> heartbeat(@RequestBody HeartbeatRequest request) {
        return ResponseEntity.ok(workerService.heartbeat(request));
    }
}
