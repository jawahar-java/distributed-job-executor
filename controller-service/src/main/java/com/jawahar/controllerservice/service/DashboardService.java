package com.jawahar.controllerservice.service;

import com.jawahar.controllerservice.dto.DashboardResponse;
import com.jawahar.controllerservice.enums.JobStatus;
import com.jawahar.controllerservice.enums.WorkerStatus;
import com.jawahar.controllerservice.repository.JobRepository;
import com.jawahar.controllerservice.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private WorkerRepository workerRepository;

    public DashboardResponse dashboard() {

        return DashboardResponse.builder()
                .submitted(jobRepository.countByStatus(JobStatus.SUBMITTED))
                .assigned(jobRepository.countByStatus(JobStatus.ASSIGNED))
                .running(jobRepository.countByStatus(JobStatus.RUNNING))
                .success(jobRepository.countByStatus(JobStatus.SUCCESS))
                .failed(jobRepository.countByStatus(JobStatus.FAILED))
                .deadLetter(jobRepository.countByStatus(JobStatus.DEAD_LETTER))
                .onlineWorkers(workerRepository.countByStatus(WorkerStatus.IDLE))
                .busyWorkers(workerRepository.countByStatus(WorkerStatus.BUSY))
                .offlineWorkers(workerRepository.countByStatus(WorkerStatus.OFFLINE))
                .build();
    }
}
