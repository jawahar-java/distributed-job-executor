package com.jawahar.controllerservice.service;

import com.jawahar.controllerservice.entity.Job;
import com.jawahar.controllerservice.entity.Worker;
import com.jawahar.controllerservice.enums.JobStatus;
import com.jawahar.controllerservice.enums.WorkerStatus;
import com.jawahar.controllerservice.repository.JobRepository;
import com.jawahar.controllerservice.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JobAssignmentService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private LoadBalancerService loadBalancerService;

    public void assignJobs() {

        List<Job> submittedJobs =
                jobRepository.findByStatus(JobStatus.SUBMITTED);

        if (submittedJobs.isEmpty()) {
            return;
        }

        for (Job job : submittedJobs) {

            Worker worker = loadBalancerService.nextWorker();

            job.setWorkerId(worker.getId());
            job.setAssignedAt(Instant.now());
            job.setStatus(JobStatus.ASSIGNED);

            worker.setStatus(WorkerStatus.BUSY);
            worker.setUpdatedAt(Instant.now());

            jobRepository.save(job);
            workerRepository.save(worker);

            System.out.println(
                    "Assigned Job " + job.getId()
                            + " to Worker "
                            + worker.getWorkerName());

        }
    }
}
