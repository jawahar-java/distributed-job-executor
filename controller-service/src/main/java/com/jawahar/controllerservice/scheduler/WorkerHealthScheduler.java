package com.jawahar.controllerservice.scheduler;

import com.jawahar.controllerservice.entity.Worker;
import com.jawahar.controllerservice.enums.WorkerStatus;
import com.jawahar.controllerservice.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class WorkerHealthScheduler {

    @Autowired
    private WorkerRepository workerRepository;

    @Scheduled(fixedRate = 60000)
    public void checkWorkerHealth() {

        Instant threshold = Instant.now().minusSeconds(60);

        List<Worker> workers = workerRepository.findAll();

        for (Worker worker : workers) {

            if (worker.getStatus() != WorkerStatus.OFFLINE &&
                    worker.getLastHeartbeat().isBefore(threshold)) {

                worker.setStatus(WorkerStatus.OFFLINE);
                worker.setUpdatedAt(Instant.now());

                workerRepository.save(worker);

                System.out.println(
                        "Worker " + worker.getWorkerName() + " marked OFFLINE");
            }
        }
    }
}