package com.jawahar.controllerservice.entity;

import com.jawahar.controllerservice.enums.WorkerStatus;
import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workers")

public class Worker {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String workerName;

    @Column(nullable = false)
    private String host;

    @Column(nullable = false)
    private Integer port;

    @Enumerated(EnumType.STRING)
    private WorkerStatus status;

    @Column(nullable = false)
    private Integer maxCapacity;

    @Column(nullable = false)
    private Integer activeJobs;

    @Column(nullable = false)
    private Instant registeredAt;

    @Column(nullable = false)
    private Instant lastHeartbeat;

    @Column(nullable = false)
    private Instant updatedAt;
}
