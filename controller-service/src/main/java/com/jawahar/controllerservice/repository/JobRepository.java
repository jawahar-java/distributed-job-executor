package com.jawahar.controllerservice.repository;

import com.jawahar.controllerservice.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {


}
