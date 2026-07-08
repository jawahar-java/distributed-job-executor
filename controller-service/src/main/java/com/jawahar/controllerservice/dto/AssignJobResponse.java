package com.jawahar.controllerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignJobResponse {

    private UUID jobId;

    private UUID workerId;

    private String message;
}