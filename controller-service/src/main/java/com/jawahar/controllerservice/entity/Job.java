package com.jawahar.controllerservice.entity;

import com.jawahar.controllerservice.enums.JobStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String jobType;
    @Column(columnDefinition = "TEXT")
    private String payload;
    private Integer retryCount;
    @Column(nullable = false)
    private Instant createdAt;
    private Instant updatedAt;
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private Instant assignedAt;
    private UUID workerId;

}
