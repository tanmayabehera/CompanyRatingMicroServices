package com.microservice.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.microservice.jobservice.entity.JobEntity;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, Long> {

}
