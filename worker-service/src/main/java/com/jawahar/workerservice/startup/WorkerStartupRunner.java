package com.jawahar.workerservice.startup;

import com.jawahar.workerservice.dto.RegisterWorkerResponse;
import com.jawahar.workerservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(
        name = "worker.registration.enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class WorkerStartupRunner implements CommandLineRunner {


    @Autowired
    private RegistrationService registrationService;

    @Override
    public void run(String... args) {

        System.out.println(">>> WorkerStartupRunner executed");

        RegisterWorkerResponse registerWorkerResponse = registrationService.registerWorker();

        System.out.println();
        System.out.println("======================================================");
        System.out.println("          WORKER REGISTRATION SUCCESSFUL");
        System.out.println("======================================================");
        System.out.println("Worker Name : worker-1");
        System.out.println("Worker ID   : " + registerWorkerResponse.getWorkerId());
        System.out.println("Status      : " + registerWorkerResponse.getStatus());
        System.out.println("Host        : localhost");
        System.out.println("Port        : 8081");
        System.out.println("======================================================");
        System.out.println();
    }
}
