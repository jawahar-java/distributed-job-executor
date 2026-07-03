package com.jawahar.workerservice.startup;

import com.jawahar.workerservice.dto.RegisterWorkerResponse;
import com.jawahar.workerservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WorkerStartupRunner implements CommandLineRunner {

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void run(String... args) {

        RegisterWorkerResponse registerWorkerResponse = registrationService.registerWorker();

        System.out.println("--------------------------------------");
        System.out.println("Worker Registered Successfully");
        System.out.println("Worker ID : " + registerWorkerResponse.getWorkerId());
        System.out.println("Status    : " + registerWorkerResponse.getStatus());
        System.out.println("--------------------------------------");
    }
}
