package com.jawahar.controllerservice.exception;

import java.util.UUID;

public class WorkerNotFoundException extends RuntimeException {
    public WorkerNotFoundException(UUID workerId) {
        super("Worker with id " + workerId + " not found.");
    }
}
