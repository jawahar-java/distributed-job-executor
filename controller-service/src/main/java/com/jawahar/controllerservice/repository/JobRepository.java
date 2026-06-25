package com.jawahar.controllerservice.repository;

import com.jawahar.controllerservice.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {


}
