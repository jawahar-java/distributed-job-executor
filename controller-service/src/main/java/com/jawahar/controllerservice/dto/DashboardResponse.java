package com.jawahar.controllerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private long submitted;
    private long assigned;
    private long running;
    private long success;
    private long failed;
    private long deadLetter;

    private long onlineWorkers;
    private long busyWorkers;
    private long offlineWorkers;
}
