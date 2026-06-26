package com.jawahar.controllerservice.controller;

import com.jawahar.controllerservice.dto.CreateJobRequest;
import com.jawahar.controllerservice.dto.CreateJobResponse;
import com.jawahar.controllerservice.dto.GetJobResponse;
import com.jawahar.controllerservice.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{jobId}")
    public ResponseEntity<GetJobResponse> getJobById(@PathVariable UUID jobId){

        return ResponseEntity.ok(jobService.getJobDetailsById(jobId));
    }
}
