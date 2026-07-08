package com.jawahar.controllerservice.service;

import com.jawahar.controllerservice.entity.Worker;
import com.jawahar.controllerservice.enums.WorkerStatus;
import com.jawahar.controllerservice.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadBalancerService {

    private int currentIndex = 0;

    @Autowired
    private WorkerRepository workerRepository;

    public Worker nextWorker() {

        List<Worker> workers =
                workerRepository.findByStatusOrderByRegisteredAtAsc(
                        WorkerStatus.IDLE);

        if(workers.isEmpty())
            return null;

        if(currentIndex >= workers.size())
            currentIndex = 0;

        Worker worker = workers.get(currentIndex);

        currentIndex++;

        return worker;
    }

}
