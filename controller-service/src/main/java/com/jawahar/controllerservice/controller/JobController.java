package com.jawahar.controllerservice.controller;

import com.jawahar.controllerservice.dto.CreateJobRequest;
import com.jawahar.controllerservice.dto.CreateJobResponse;
import com.jawahar.controllerservice.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    JobService jobService;

    @PostMapping("/submit")
    public ResponseEntity<CreateJobResponse> submitJob(
            @Valid @RequestBody CreateJobRequest request) {

        return ResponseEntity.ok(jobService.submitJob(request));
    }
}
