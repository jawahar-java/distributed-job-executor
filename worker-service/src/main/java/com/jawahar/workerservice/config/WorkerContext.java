package com.jawahar.workerservice.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class WorkerContext {
    private UUID workerId;

    private String workerName;
}
