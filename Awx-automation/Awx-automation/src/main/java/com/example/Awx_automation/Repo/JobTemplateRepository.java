package com.example.Awx_automation.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Awx_automation.Entity.JobTemplateResponse;

public interface JobTemplateRepository extends MongoRepository<JobTemplateResponse, String>{

}
