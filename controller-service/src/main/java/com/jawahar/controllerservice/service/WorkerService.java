package com.jawahar.controllerservice.service;

import com.jawahar.controllerservice.dto.HeartbeatRequest;
import com.jawahar.controllerservice.dto.HeartbeatResponse;
import com.jawahar.controllerservice.dto.RegisterWorkerRequest;
import com.jawahar.controllerservice.dto.RegisterWorkerResponse;
import com.jawahar.controllerservice.entity.Worker;
import com.jawahar.controllerservice.enums.WorkerStatus;
import com.jawahar.controllerservice.exception.WorkerNotFoundException;
import com.jawahar.controllerservice.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;


    public RegisterWorkerResponse registerWorker(RegisterWorkerRequest request) {

        Optional<Worker> existingWorker =
                workerRepository.findByWorkerName(request.getWorkerName());

        // Worker already exists -> Update details
        if (existingWorker.isPresent()) {

            Worker worker = existingWorker.get();

            worker.setHost(request.getHost());
            worker.setPort(request.getPort());
            worker.setMaxCapacity(request.getMaxCapacity());

            worker.setStatus(WorkerStatus.REGISTERED);
            worker.setLastHeartbeat(Instant.now());
            worker.setUpdatedAt(Instant.now());
            worker.setTotalJobsExecuted(0);

            workerRepository.save(worker);

            return buildResponse(worker, "Worker already registered. Details updated successfully.");
        }

        // New Worker Registration
        Worker worker = new Worker();

        worker.setId(UUID.randomUUID());
        worker.setWorkerName(request.getWorkerName());
        worker.setHost(request.getHost());
        worker.setPort(request.getPort());
        worker.setMaxCapacity(request.getMaxCapacity());

        worker.setStatus(WorkerStatus.REGISTERED);
        worker.setActiveJobs(0);

        worker.setRegisteredAt(Instant.now());
        worker.setLastHeartbeat(Instant.now());
        worker.setUpdatedAt(Instant.now());

        workerRepository.save(worker);

        return buildResponse(worker, "Worker registered successfully.");
    }

    private RegisterWorkerResponse buildResponse(Worker worker, String message) {
        return RegisterWorkerResponse.builder()
                .workerId(worker.getId())
                .status(worker.getStatus().name())
                .message(message)
                .build();
    }

    public HeartbeatResponse heartbeat(HeartbeatRequest request){

        Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow(() ->
                        new WorkerNotFoundException(request.getWorkerId()));

        Instant now = Instant.now();

        worker.setLastHeartbeat(now);
        worker.setUpdatedAt(now);
        worker.setStatus(WorkerStatus.IDLE);

        workerRepository.save(worker);

        return HeartbeatResponse.builder()
                .workerId(worker.getId())
                .status(worker.getStatus().name())
                .message("Heartbeat received successfully.")
                .lastHeartbeat(worker.getLastHeartbeat())
                .build();

    }
}
