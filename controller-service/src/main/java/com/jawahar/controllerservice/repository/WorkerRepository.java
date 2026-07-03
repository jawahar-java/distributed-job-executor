package com.jawahar.controllerservice.repository;

import com.jawahar.controllerservice.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {


        Optional<Worker> findByWorkerName(String workerName);

}
