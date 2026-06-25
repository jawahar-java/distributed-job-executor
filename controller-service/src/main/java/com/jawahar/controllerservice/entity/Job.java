package com.jawahar.controllerservice.entity;

import com.jawahar.controllerservice.enums.JobStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    private UUID id;
    private String jobType;
    private String payload;
    private Integer retryCount;
    private Instant createdAt;
    private Instant updatedAt;
    @Enumerated(EnumType.STRING)
    private JobStatus status;

}
