package com.jawahar.controllerservice.dto;

import com.jawahar.controllerservice.enums.JobStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class GetJobResponse {

    private UUID jobId;

    private String jobType;

    private String payload;

    private JobStatus status;

    private Integer retryCount;

    private Instant createdAt;

    private Instant updatedAt;

}
