package com.jawahar.controllerservice.dto;

import com.jawahar.controllerservice.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobResponse {

    private UUID jobId;

    private JobStatus status;

    private String message;

}