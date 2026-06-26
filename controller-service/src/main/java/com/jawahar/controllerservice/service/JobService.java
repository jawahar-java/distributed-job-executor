package com.jawahar.controllerservice.service;


import com.jawahar.controllerservice.dto.CreateJobRequest;
import com.jawahar.controllerservice.dto.CreateJobResponse;
import com.jawahar.controllerservice.dto.GetJobResponse;
import com.jawahar.controllerservice.entity.Job;
import com.jawahar.controllerservice.enums.JobStatus;
import com.jawahar.controllerservice.exception.JobNotFoundException;
import com.jawahar.controllerservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    //Submit Job
    public CreateJobResponse submitJob(CreateJobRequest request) {

        Job job = new Job();
        job.setId(UUID.randomUUID());
        job.setJobType(request.getJobType());
        job.setPayload(request.getPayload());
        job.setStatus(JobStatus.SUBMITTED);
        job.setRetryCount(0);
        job.setCreatedAt(Instant.now());
        job.setUpdatedAt(Instant.now());

        jobRepository.save(job);

        return CreateJobResponse.builder()
                .jobId(job.getId())
                .status(job.getStatus())
                .message("Job submitted successfully")
                .build();
    }

    //Get Job Details by ID
    public GetJobResponse getJobDetailsById(UUID jobId) {

       Job  job= this.jobRepository.findById(jobId).orElseThrow(() -> new JobNotFoundException(jobId));

       return GetJobResponse.builder()
               .jobId(job.getId())
               .status(job.getStatus())
               .jobType(job.getJobType())
               .payload(job.getPayload())
               .retryCount(job.getRetryCount())
               .createdAt(job.getCreatedAt())
               .updatedAt(job.getUpdatedAt())
               .build();

    }
}
