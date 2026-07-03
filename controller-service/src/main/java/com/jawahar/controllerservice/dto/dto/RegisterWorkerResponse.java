package com.jawahar.controllerservice.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterWorkerResponse {

    private UUID workerId;
    private String status;
    private String message;
}
