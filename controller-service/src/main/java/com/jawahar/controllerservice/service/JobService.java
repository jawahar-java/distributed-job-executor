package com.jawahar.controllerservice.service;


import com.jawahar.controllerservice.dto.*;
import com.jawahar.controllerservice.entity.Job;
import com.jawahar.controllerservice.entity.Worker;
import com.jawahar.controllerservice.enums.JobStatus;
import com.jawahar.controllerservice.enums.WorkerStatus;
import com.jawahar.controllerservice.exception.JobNotFoundException;
import com.jawahar.controllerservice.repository.JobRepository;
import com.jawahar.controllerservice.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private WorkerRepository workerRepository;

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

    public NextJobResponse getNextJob(UUID workerId) {

        Job job = jobRepository
                .findFirstByWorkerIdAndStatus(
                        workerId,
                        JobStatus.ASSIGNED)
                .orElse(null);

        if (job == null) {
            return null;
        }

        job.setStatus(JobStatus.RUNNING);

        job.setUpdatedAt(Instant.now());

        jobRepository.save(job);

        return NextJobResponse.builder()
                .jobId(job.getId())
                .jobType(job.getJobType())
                .payload(job.getPayload())
                .status(job.getStatus().name())
                .build();
    }

    public void completeJob(CompleteJobRequest request){

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow();

        Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow();

        job.setStatus(JobStatus.SUCCESS);
        job.setUpdatedAt(Instant.now());

        worker.setStatus(WorkerStatus.IDLE);
        worker.setUpdatedAt(Instant.now());
        worker.setTotalJobsExecuted(
                worker.getTotalJobsExecuted() + 1);

        jobRepository.save(job);
        workerRepository.save(worker);

    }

    public void updateStatus(UpdateJobStatusRequest request){

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow();

        Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow();

        if ("SUCCESS".equals(request.getStatus())) {

            job.setStatus(JobStatus.SUCCESS);

        } else {

            job.setRetryCount(job.getRetryCount() + 1);

            if(job.getRetryCount() < 3){

                job.setStatus(JobStatus.SUBMITTED);

            }else{

                job.setStatus(JobStatus.DEAD_LETTER);

            }

        }

        job.setUpdatedAt(Instant.now());

        worker.setStatus(WorkerStatus.IDLE);
        worker.setUpdatedAt(Instant.now());

        jobRepository.save(job);
        workerRepository.save(worker);

    }
}
