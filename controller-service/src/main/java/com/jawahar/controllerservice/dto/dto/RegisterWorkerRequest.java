package com.jawahar.controllerservice.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterWorkerRequest {
    private String workerName;
    private String host;
    private Integer port;
    private Integer maxCapacity;
}
