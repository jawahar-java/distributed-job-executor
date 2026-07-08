package com.jawahar.controllerservice.repository;

import com.jawahar.controllerservice.entity.Job;
import com.jawahar.controllerservice.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {

    List<Job> findByStatus(JobStatus status);

    Optional<Job> findFirstByWorkerIdAndStatus(
            UUID workerId,
            JobStatus status);

    long countByStatus(JobStatus status);
}
