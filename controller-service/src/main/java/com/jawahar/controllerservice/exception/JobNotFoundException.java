package com.jawahar.controllerservice.exception;


import java.util.UUID;

public class JobNotFoundException extends RuntimeException {

    public JobNotFoundException(UUID jobId) {
        super("Job with id: "+ jobId+ " not found");
    }
}
