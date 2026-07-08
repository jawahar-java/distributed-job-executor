package com.jawahar.controllerservice.controller;

import com.jawahar.controllerservice.dto.*;
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

    @GetMapping("/worker/{workerId}/next")
    public ResponseEntity<NextJobResponse> getNextJob(
            @PathVariable UUID workerId){

        NextJobResponse response =
                jobService.getNextJob(workerId);

        if(response==null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> completeJob(
            @RequestBody CompleteJobRequest request){

        jobService.completeJob(request);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/status")
    public ResponseEntity<Void> updateStatus(
            @RequestBody UpdateJobStatusRequest request){

        jobService.updateStatus(request);

        return ResponseEntity.ok().build();

    }
}
