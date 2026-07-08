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
public class NextJobResponse {

    private UUID jobId;

    private String jobType;

    private String payload;

    private String status;
}
