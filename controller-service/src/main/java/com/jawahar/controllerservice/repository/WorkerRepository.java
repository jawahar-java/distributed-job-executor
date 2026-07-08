package com.jawahar.controllerservice.repository;

import com.jawahar.controllerservice.entity.Worker;
import com.jawahar.controllerservice.enums.WorkerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {


        Optional<Worker> findByWorkerName(String workerName);

        List<Worker> findByStatus(WorkerStatus status);

        List<Worker> findByStatusOrderByRegisteredAtAsc(
                WorkerStatus status);

        long countByStatus(WorkerStatus status);

}
